package com.example.transaction_service.service.account;

import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.repository.AccountRepository;
import com.example.transaction_service.model.client.entity.Client;
import com.example.transaction_service.model.account.type.enumeration.AccountTypeEnumeration;

import java.util.List;

/**
 * Абстрактный класс, выполняющий действия, связанные с пользовательскими аккаунтами
 * @param <A> Тип, являющийся пользовательским аккаунтом {@link Account} или его наследником
 */
public abstract class AbstractAccountService<A extends Account> {
    protected AccountRepository accountRepository;

    public AbstractAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Создать и сохранить новый несуществующий клиентский аккаунт {@link Account}
     * @param clientId Id существующего клиента {@link Client}
     * @param accountTypeId Id существующего типа клиентского аккаунта {@link AccountTypeEnumeration}
     */
    public abstract void save(long clientId, long accountTypeId);

    /**
     * Получить существующий клиентский аккаунт {@link Account} по его Id
     * @param id Id существующего клиентского аккаунта
     * @return Существующий клиентский аккаунт {@link Account}
     */
    public abstract A getById(long id);

    /**
     * Получить список {@link List} существующих клиентских аккаунтов {@link Account} по Id и типу клиентского аккаунта {@link AccountTypeEnumeration}
     * @param clientId Id существующего клиента
     * @return Список {@link List} существующих клиентских аккаунтов {@link Account} определённого клиента {@link Client} по его Id и типу клиентского аккаунта {@link AccountTypeEnumeration}
     */
    public abstract List<A> getByClientIdAndAccountType(long clientId);

    /**
     * Получить список {@link List} существующих клиентских аккаунтов {@link Account} по Id клиентского аккаунта {@link Client}
     * @param id Id существующего клиента
     * @return Список {@link List} существующих клиентских аккаунтов {@link Account} определённого клиента {@link Client} по его Id
     */
    public List<Account> getByClientId(long id) {
        return accountRepository.findAccountByClientId(id);
    }
}
