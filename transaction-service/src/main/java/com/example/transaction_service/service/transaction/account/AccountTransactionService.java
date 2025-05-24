package com.example.transaction_service.service.transaction.account;

import com.example.transaction_service.model.transaction.entity.Transaction;
import com.example.transaction_service.service.transaction.TransactionService;

public abstract class AccountTransactionService<T extends Transaction> implements TransactionService<T> {
    @Override
    public abstract double insert(long recipientId, double amount);

    @Override
    public abstract double transfer(long senderId, long recipientId, double amount);

    @Override
    public abstract boolean isValidInsert(T transaction);
}
