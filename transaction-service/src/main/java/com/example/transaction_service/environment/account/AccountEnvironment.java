package com.example.transaction_service.environment.account;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccountEnvironment {
    private final double ACCOUNT_CREDIT_START_BALANCE;

    public AccountEnvironment(@Value("${account.credit.start-balance}") double ACCOUNT_CREDIT_START_BALANCE) {
        this.ACCOUNT_CREDIT_START_BALANCE = ACCOUNT_CREDIT_START_BALANCE;
    }

    public double getACCOUNT_CREDIT_START_BALANCE() {
        return ACCOUNT_CREDIT_START_BALANCE;
    }
}
