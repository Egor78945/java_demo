package com.example.transaction_service.service.transaction.account;

import com.example.transaction_service.model.transaction.entity.Transaction;

public abstract class CreditAccountTransactionService<T extends Transaction> extends AccountTransactionService<T> {
    @Override
    public abstract double insert(long recipientAccountId, double amount);

    @Override
    public abstract double transfer(long senderAccountId, long recipientAccountId, double amount);

    @Override
    public abstract boolean isValidInsert(T transaction);
}
