package com.example.transaction_service.service.transaction.account;

import com.example.transaction_service.model.transaction.entity.Transaction;

/**
 * Реализация абстрактного сервиса по работе с транзакциями между клиентскими аккаунтами {@link AbstractAccountTransactionService}
 * для работы с клиентскими аккаунтами типа <b>CREDIT</b>
 * @param <T> Тип, являющийся транзакцией {@link Transaction} или её наследником
 */
public abstract class AbstractCreditAccountTransactionService<T extends Transaction> extends AbstractAccountTransactionService<T> {
    @Override
    public abstract double insert(long recipientAccountId, double amount);

    @Override
    public abstract double transfer(long senderAccountId, long recipientAccountId, double amount);

    @Override
    public abstract boolean isValidInsert(T transaction);

    @Override
    public abstract boolean isValidTransfer(T transaction);
}
