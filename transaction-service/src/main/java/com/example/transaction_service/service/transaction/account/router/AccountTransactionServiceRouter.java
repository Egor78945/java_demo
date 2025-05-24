package com.example.transaction_service.service.transaction.account.router;

import com.example.transaction_service.model.account.type.enumeration.AccountTypeEnumeration;
import com.example.transaction_service.model.transaction.entity.Transaction;
import com.example.transaction_service.service.transaction.account.AccountTransactionService;

import java.util.Optional;

public interface AccountTransactionServiceRouter<T extends AccountTransactionService<Transaction>> {
    Optional<T> getByAccountTypeEnumeration(AccountTypeEnumeration accountTypeEnumeration);
}
