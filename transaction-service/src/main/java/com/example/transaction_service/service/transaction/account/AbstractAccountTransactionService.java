package com.example.transaction_service.service.transaction.account;

import com.example.transaction_service.model.transaction.entity.Transaction;
import com.example.transaction_service.service.transaction.TransactionService;

/**
 * Абстрактный класс, реализующий интерфейс, предоставляющий функционал по работе с транзациями {@link TransactionService}
 *
 * @param <T> Тип, являющийся транзакцией {@link Transaction} или его наследником
 */
public abstract class AbstractAccountTransactionService<T extends Transaction> implements TransactionService<T> {
    @Override
    public abstract double insert(long recipientId, double amount);

    @Override
    public abstract double transfer(long senderId, long recipientId, double amount);

    @Override
    public abstract boolean isValidInsert(T transaction);

    @Override
    public abstract boolean isValidTransfer(T transaction);
}
