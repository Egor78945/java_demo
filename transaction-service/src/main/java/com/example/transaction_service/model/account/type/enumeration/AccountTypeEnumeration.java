package com.example.transaction_service.model.account.type.enumeration;

import org.apache.commons.collections4.map.UnmodifiableMap;

import java.util.Map;
import java.util.Optional;

public enum AccountTypeEnumeration {
    DEBIT(1), CREDIT(2);
    private final long id;
    private static final Map<String, AccountTypeEnumeration> accountTypeEnumerationByName;
    private static final Map<Long, AccountTypeEnumeration> accountTypeEnumerationById;

    AccountTypeEnumeration(long id) {
        this.id = id;
    }

    static {
        accountTypeEnumerationByName = Map.of(DEBIT.name(), DEBIT, CREDIT.name(), CREDIT);
        accountTypeEnumerationById = Map.of(DEBIT.getId(), DEBIT, CREDIT.getId(), CREDIT);
    }

    public long getId() {
        return id;
    }

    public static Optional<AccountTypeEnumeration> getById(long id) {
        return Optional.ofNullable(accountTypeEnumerationById.get(id));
    }

    public static Optional<AccountTypeEnumeration> getByName(String name) {
        return Optional.ofNullable(accountTypeEnumerationByName.get(name));
    }
}
