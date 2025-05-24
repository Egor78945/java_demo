package com.example.transaction_service.service.transaction;

public interface TransactionService<T> {
    double insert(long recipientId, double amount);

    double transfer(long senderId, long recipientId, double amount);

    boolean isValidInsert(T transaction);

    boolean isValidTransfer(T transaction);
}
