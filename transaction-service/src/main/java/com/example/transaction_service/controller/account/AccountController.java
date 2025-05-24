package com.example.transaction_service.controller.account;

import com.example.transaction_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.transaction_service.exception.NotFoundException;
import com.example.transaction_service.model.account.entity.Account;
import com.example.transaction_service.model.account.type.enumeration.AccountTypeEnumeration;
import com.example.transaction_service.service.account.AbstractAccountService;
import com.example.transaction_service.service.account.router.AccountServiceRouter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/account")
@CommonControllerExceptionHandler
public class AccountController {
    private final AccountServiceRouter<AbstractAccountService<Account>> accountServiceRouter;

    public AccountController(@Qualifier("accountServiceRouterManager") AccountServiceRouter<AbstractAccountService<Account>> accountServiceRouter) {
        this.accountServiceRouter = accountServiceRouter;
    }

    @PostMapping("/registration")
    public void registerAccount(@RequestParam(value = "clientId", defaultValue = "-1") long clientId, @RequestParam(value = "accountTypeId", defaultValue = "1") long accountTypeId) {
        accountServiceRouter.getByAccountTypeEnumeration(AccountTypeEnumeration.getById(accountTypeId)
                        .orElseThrow(() -> new NotFoundException(String.format("unknown account type\naccount type id : %s", accountTypeId))))
                .save(clientId, accountTypeId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Account>> getAccountsByClientId(@PathVariable("id") long id, @RequestParam(value = "accountTypeId", defaultValue = "-1") long accountTypeId) {
        AbstractAccountService<Account> accountService;
        if (accountTypeId != -1) {
            accountService = accountServiceRouter.getByAccountTypeEnumeration(AccountTypeEnumeration.getById(accountTypeId).orElseThrow(() ->
                    new NotFoundException(String.format("unknown account type id\nid : %s", accountTypeId))));
            return ResponseEntity.ok(accountService.getByClientIdAndAccountType(id));
        } else {
            accountService = accountServiceRouter.getByAccountTypeEnumeration(AccountTypeEnumeration.DEBIT);
            return ResponseEntity.ok(accountService.getByClientId(id));
        }

    }

}
