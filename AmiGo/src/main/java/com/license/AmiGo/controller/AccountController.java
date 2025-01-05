package com.license.AmiGo.controller;

import com.license.AmiGo.model.*;
import com.license.AmiGo.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    /**
     * API to add a new account to the system.
     *
     * @param account The account information to be added.
     */
    @Operation(
            summary = "Add a new account",
            description = "This endpoint allows the user to add a new account to the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid account data provided")
    })
    @PostMapping("/add")
    public void add(
            @Parameter(description = "Account object that needs to be added", required = true)
            @RequestBody Account account
    ) {
        accountService.saveAccount(account);
    }

    /**
     * API to get all the accounts in the system.
     *
     * @return A list of all accounts stored in the system.
     */
    @Operation(
            summary = "Get all accounts",
            description = "This endpoint retrieves a list of all accounts currently stored in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all accounts retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No accounts found")
    })
    @GetMapping("/getAll")
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    /**
     * API to delete all accounts from the system.
     * This action removes all accounts permanently.
     */
    @Operation(
            summary = "Delete all accounts",
            description = "This endpoint deletes all accounts from the system. This action is irreversible."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All accounts deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Failed to delete accounts due to an error")
    })
    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        accountService.deleteAll();
    }
}
