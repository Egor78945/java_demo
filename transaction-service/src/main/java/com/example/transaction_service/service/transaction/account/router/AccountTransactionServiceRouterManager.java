package com.example.transaction_service.service.transaction.account.router;

import com.example.transaction_service.model.account.type.enumeration.AccountTypeEnumeration;
import com.example.transaction_service.model.transaction.entity.Transaction;
import com.example.transaction_service.service.transaction.account.AccountTransactionService;
import com.example.transaction_service.service.transaction.account.CreditAccountTransactionService;
import com.example.transaction_service.service.transaction.account.DebitAccountTransactionService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AccountTransactionServiceRouterManager implements AccountTransactionServiceRouter<AccountTransactionService<Transaction>> {
    private final Map<AccountTypeEnumeration, AccountTransactionService<Transaction>> transactionServiceByAccountTypeEnumeration;

    public AccountTransactionServiceRouterManager(DebitAccountTransactionService<Transaction> debitAccountTransactionService, CreditAccountTransactionService<Transaction> creditAccountTransactionService) {
        this.transactionServiceByAccountTypeEnumeration = Map.of(AccountTypeEnumeration.DEBIT, debitAccountTransactionService, AccountTypeEnumeration.CREDIT, creditAccountTransactionService);
    }

    @Override
    public Optional<AccountTransactionService<Transaction>> getByAccountTypeEnumeration(AccountTypeEnumeration accountTypeEnumeration) {
        return Optional.ofNullable(transactionServiceByAccountTypeEnumeration.get(accountTypeEnumeration));
    }
}
