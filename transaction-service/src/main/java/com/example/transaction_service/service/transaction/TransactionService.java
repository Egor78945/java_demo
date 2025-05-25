package com.example.transaction_service.service.transaction;

import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.model.transaction.entity.Transaction;

/**
 * Интерфейс, предоставляющий функционал по управлению транзакциями между клиентскими аккаунтами {@link Account}
 */
public interface TransactionService<T> {
    /**
     * Провести транзакцию внесения средств
     * @param recipientId Id получателя средств
     * @param amount Сумма транзакции
     * @return Текущий баланс получателя средств
     */
    double insert(long recipientId, double amount);

    /**
     * Провести транзакцию перевода средств
     * @param senderId Id отправителя средств
     * @param recipientId Id получателя средств
     * @param amount Сумма транзакции
     * @return Текущий баланс отправителя средств
     */
    double transfer(long senderId, long recipientId, double amount);

    /**
     * Проверить, является ли транзакция внесения средств правильной
     * @param transaction транзакция внесения средств {@link Transaction}
     * @return Результат проверки {@link Boolean}
     */
    boolean isValidInsert(T transaction);

    /**
     * Проверить, является ли транзакция перевода средств правильной
     * @param transaction транзакция перевода средств {@link Transaction}
     * @return Результат проверки {@link Boolean}
     */
    boolean isValidTransfer(T transaction);
}
