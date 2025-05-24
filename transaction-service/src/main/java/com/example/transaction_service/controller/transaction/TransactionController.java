package com.example.transaction_service.controller.transaction;

import com.example.transaction_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.transaction_service.exception.NotFoundException;
import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.model.account.type.enumeration.AccountTypeEnumeration;
import com.example.transaction_service.model.transaction.entity.Transaction;
import com.example.transaction_service.service.account.AbstractAccountService;
import com.example.transaction_service.service.transaction.TransactionService;
import com.example.transaction_service.service.transaction.account.AccountTransactionService;
import com.example.transaction_service.service.transaction.account.router.AccountTransactionServiceRouter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@CommonControllerExceptionHandler
public class TransactionController {
    private final AccountTransactionServiceRouter<AccountTransactionService<Transaction>> transactionServiceRouter;
    private final AbstractAccountService<Account> accountService;

    public TransactionController(@Qualifier("accountTransactionServiceRouterManager") AccountTransactionServiceRouter<AccountTransactionService<Transaction>> transactionServiceRouter, @Qualifier("debitAccountServiceManager") AbstractAccountService<Account> accountService) {
        this.transactionServiceRouter = transactionServiceRouter;
        this.accountService = accountService;
    }

    @PutMapping("/insert")
    public ResponseEntity<Double> insertMoney(@RequestParam("recipientId") long recipientId, @RequestParam("amount") double amount) {
        AccountTypeEnumeration accountTypeEnumeration = AccountTypeEnumeration.getById(accountService.getById(recipientId).getAccountType().getId()).orElseThrow(() -> new NotFoundException("account type enumeration by id is not found"));
        TransactionService<Transaction> transactionService = transactionServiceRouter.getByAccountTypeEnumeration(accountTypeEnumeration).orElseThrow(() -> new NotFoundException(String.format("account service not found by account type enumeration\nAccountTypeEnumeration : %s", accountTypeEnumeration)));
        return ResponseEntity.ok(transactionService.insert(recipientId, amount));
    }

    @PutMapping("/transfer")
    public ResponseEntity<Double> transferMoney(@RequestParam("senderId") long senderId, @RequestParam("recipientId") long recipientId, @RequestParam("amount") double amount) {
        AccountTypeEnumeration accountTypeEnumeration = AccountTypeEnumeration.getById(accountService.getById(senderId).getAccountType().getId()).orElseThrow(() -> new NotFoundException("account type enumeration by id is not found"));
        TransactionService<Transaction> transactionService = transactionServiceRouter.getByAccountTypeEnumeration(accountTypeEnumeration).orElseThrow(() -> new NotFoundException(String.format("account service not found by account type enumeration\nAccountTypeEnumeration : %s", accountTypeEnumeration)));
        return ResponseEntity.ok(transactionService.transfer(senderId, recipientId, amount));
    }
}
