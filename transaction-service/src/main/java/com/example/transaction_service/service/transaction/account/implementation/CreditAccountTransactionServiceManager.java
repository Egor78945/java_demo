package com.example.transaction_service.service.transaction.account.implementation;

import com.example.transaction_service.environment.account.AccountEnvironment;
import com.example.transaction_service.exception.NotFoundException;
import com.example.transaction_service.exception.TransactionException;
import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.model.account.type.enumeration.AccountTypeEnumeration;
import com.example.transaction_service.model.transaction.entity.Transaction;
import com.example.transaction_service.model.transaction.type.enumeration.TransactionTypeEnumeration;
import com.example.transaction_service.repository.AccountRepository;
import com.example.transaction_service.repository.TransactionRepository;
import com.example.transaction_service.repository.TransactionTypeRepository;
import com.example.transaction_service.service.transaction.account.AbstractCreditAccountTransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация абстрактной реализации сервиса по работе странзакциями {@link AbstractCreditAccountTransactionService} типа <b>CREDIT</b>
 */
@Service
public class CreditAccountTransactionServiceManager extends AbstractCreditAccountTransactionService<Transaction> {
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final AccountEnvironment accountEnvironment;
    private final TransactionTypeRepository transactionTypeRepository;

    public CreditAccountTransactionServiceManager(TransactionRepository transactionRepository, AccountRepository accountRepository, TransactionTypeRepository transactionTypeRepository, AccountEnvironment accountEnvironment) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.accountEnvironment = accountEnvironment;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public double insert(long recipientAccountId, double amount) {
        Account recipient = accountRepository.findAccountById(recipientAccountId).orElseThrow(() -> new NotFoundException(String.format("account is not found\nid : %s", recipientAccountId)));
        Transaction transaction = new Transaction(recipient, recipient, transactionTypeRepository.findById(TransactionTypeEnumeration.INSERT.getId()).orElseThrow(() -> new NotFoundException(String.format("transaction type is not found by id\nid : %s", TransactionTypeEnumeration.INSERT.getId()))), amount, Timestamp.valueOf(LocalDateTime.now()));
        if (isValidInsert(transaction)) {
            recipient.setBalance(recipient.getBalance() + amount);
            transactionRepository.save(transaction);
            accountRepository.save(recipient);
            return recipient.getBalance();
        } else {
            throw new TransactionException(String.format("insert transaction can not be done successfully\nRecipient : %s\ntransaction amount : %s", recipient, amount));
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public double transfer(long senderAccountId, long recipientAccountId, double amount) {
        Account recipient = accountRepository.findAccountById(recipientAccountId).orElseThrow(() -> new NotFoundException(String.format("account is not found\nid : %s", recipientAccountId)));
        Account sender = accountRepository.findAccountById(senderAccountId).orElseThrow(() -> new NotFoundException(String.format("account is not found\nid : %s", senderAccountId)));
        Transaction transaction = new Transaction(sender, recipient, transactionTypeRepository.findById(TransactionTypeEnumeration.TRANSFER.getId()).orElseThrow(() -> new NotFoundException(String.format("transaction type is not found by id\nid : %s", TransactionTypeEnumeration.TRANSFER.getId()))), amount, Timestamp.valueOf(LocalDateTime.now()));
        if (isValidTransfer(transaction)) {
            recipient.setBalance(recipient.getBalance() + amount);
            sender.setBalance(sender.getBalance() - amount);
            transactionRepository.save(transaction);
            accountRepository.saveAll(List.of(sender, recipient));
            return sender.getBalance();
        } else {
            throw new TransactionException(String.format("insert transaction can not be done successfully\nSender : %s\nRecipient : %s\ntransaction amount : %s", sender, recipient, amount));
        }
    }

    @Override
    public boolean isValidInsert(Transaction transaction) {
        return transaction.getRecipient().getAccountType().getId() == AccountTypeEnumeration.CREDIT.getId() &&
                transaction.getAmount() <= accountEnvironment.getACCOUNT_TRANSACTION_MAX_AMOUNT() &&
                transaction.getAmount() >= accountEnvironment.getACCOUNT_TRANSACTION_MIN_AMOUNT() &&
                transaction.getRecipient().getBalance() + transaction.getAmount() <= accountEnvironment.getACCOUNT_BALANCE_MAX_AMOUNT();

    }

    @Override
    public boolean isValidTransfer(Transaction transaction) {
        return !transaction.getSender().getId().equals(transaction.getRecipient().getId()) &&
                transaction.getSender().getAccountType().getId() == AccountTypeEnumeration.CREDIT.getId() &&
                transaction.getAmount() <= accountEnvironment.getACCOUNT_TRANSACTION_MAX_AMOUNT() &&
                transaction.getAmount() >= accountEnvironment.getACCOUNT_TRANSACTION_MIN_AMOUNT() &&
                transaction.getRecipient().getBalance() + transaction.getAmount() <= accountEnvironment.getACCOUNT_BALANCE_MAX_AMOUNT() &&
                transaction.getSender().getBalance() >= accountEnvironment.getACCOUNT_CREDIT_START_BALANCE() &&
                transaction.getSender().getBalance() - transaction.getAmount() >= 0;
    }
}
