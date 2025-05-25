package com.example.transaction_service.service.transaction.account.router;

import com.example.transaction_service.model.account.type.enumeration.AccountTypeEnumeration;
import com.example.transaction_service.model.transaction.entity.Transaction;
import com.example.transaction_service.service.transaction.account.AbstractAccountTransactionService;
import com.example.transaction_service.service.transaction.account.AbstractCreditAccountTransactionService;
import com.example.transaction_service.service.transaction.account.AbstractDebitAccountTransactionService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * Реализация маршрутизатора абстрактных сервисов по работе с транзакциями между клиентскими аккаунтами {@link AccountTransactionServiceRouter}
 */
@Service
public class AccountTransactionServiceRouterManager implements AccountTransactionServiceRouter<AbstractAccountTransactionService<Transaction>> {
    private final Map<AccountTypeEnumeration, AbstractAccountTransactionService<Transaction>> transactionServiceByAccountTypeEnumeration;

    public AccountTransactionServiceRouterManager(AbstractDebitAccountTransactionService<Transaction> debitAccountTransactionService, AbstractCreditAccountTransactionService<Transaction> creditAccountTransactionService) {
        this.transactionServiceByAccountTypeEnumeration = Map.of(AccountTypeEnumeration.DEBIT, debitAccountTransactionService, AccountTypeEnumeration.CREDIT, creditAccountTransactionService);
    }

    @Override
    public Optional<AbstractAccountTransactionService<Transaction>> getByAccountTypeEnumeration(AccountTypeEnumeration accountTypeEnumeration) {
        return Optional.ofNullable(transactionServiceByAccountTypeEnumeration.get(accountTypeEnumeration));
    }
}
