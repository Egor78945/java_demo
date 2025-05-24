package com.example.transaction_service.service.account;

import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.repository.AccountRepository;

import java.util.List;

public abstract class AbstractDebitAccountService <A extends Account> extends AbstractAccountService<A>{

    public AbstractDebitAccountService(AccountRepository accountRepository) {
        super(accountRepository);
    }

    @Override
    public abstract void save(long clientId, long accountTypeId);

    @Override
    public abstract A getById(long id);

    @Override
    public abstract List<A> getByClientIdAndAccountType(long clientId);
}
