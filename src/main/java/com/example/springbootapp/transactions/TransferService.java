package com.example.springbootapp.transactions;

import com.example.springbootapp.Command;
import com.example.springbootapp.exceptions.AccountNotFoundException;
import com.example.springbootapp.exceptions.NotEnoughMoneyException;
import com.example.springbootapp.product.services.GetProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TransferService implements Command<TransferDTO, String> {

    private static final Logger logger = LoggerFactory.getLogger(GetProductService.class);
    private final BankAccountRepository bankAccountRepository;

    public TransferService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public ResponseEntity<String> execute(TransferDTO transfer) {

        Optional<BankAccount> optionalFrom = bankAccountRepository.findById(transfer.getFromUser());
        Optional<BankAccount> optionalTo = bankAccountRepository.findById(transfer.getToUser());

        if (optionalFrom.isEmpty() || optionalTo.isEmpty()) {
            throw new AccountNotFoundException();
        }

        BankAccount from = optionalFrom.get();
        BankAccount to = optionalTo.get();

        add(to, transfer.getAmount());
        logger.info("Amount after adding and before deducting: " + bankAccountRepository.findById(to.getName()));
        deduct(from, transfer.getAmount());

        return ResponseEntity.status(HttpStatus.OK).body("Transfer made successfully.");
    }

    private void deduct(BankAccount bankAccount, double amount) {
        if (bankAccount.getBalance() >= amount) {
            bankAccount.setBalance(bankAccount.getBalance() - amount);
        } else
            throw new NotEnoughMoneyException();
    }

    private void add(BankAccount bankAccount, double amount) {
        bankAccount.setBalance(bankAccount.getBalance() + amount);
    }
}
