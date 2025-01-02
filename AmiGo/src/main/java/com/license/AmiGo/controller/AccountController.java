package com.license.AmiGo.controller;

import com.license.AmiGo.model.*;
import com.license.AmiGo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/add")
    public void add(@RequestBody Account account) {
        accountService.saveAccount(account);
    }
    @GetMapping("/getAll")
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }
    @PatchMapping("/deleteAccountById")
    public void deleteAccountById(@RequestBody Long account_id) {
        accountService.deleteAccountById(account_id);
    }
}
