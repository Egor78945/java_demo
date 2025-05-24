package com.example.transaction_service.service.account;

import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.repository.AccountRepository;

import java.util.List;

public abstract class AbstractAccountService<A extends Account> {
    protected AccountRepository accountRepository;

    public AbstractAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public abstract void save(long clientId, long accountTypeId);

    public abstract A getById(long id);

    public abstract List<A> getByClientIdAndAccountType(long clientId);

    public List<Account> getByClientId(long id) {
        return accountRepository.findAccountByClientId(id);
    }
}
