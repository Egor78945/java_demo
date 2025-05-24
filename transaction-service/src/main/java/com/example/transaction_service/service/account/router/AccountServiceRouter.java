package com.example.transaction_service.service.account.router;

import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.model.account.type.enumeration.AccountTypeEnumeration;
import com.example.transaction_service.service.account.AbstractAccountService;

public interface AccountServiceRouter<A extends AbstractAccountService<? extends Account>> {
    A getByAccountTypeEnumeration(AccountTypeEnumeration accountTypeEnumeration);
}
