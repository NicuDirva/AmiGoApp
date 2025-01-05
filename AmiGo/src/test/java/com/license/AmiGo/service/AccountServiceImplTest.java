package com.license.AmiGo.service;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.repository.AccountRepository;
import com.license.AmiGo.service.implement.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveAccount() {
        Account account = new Account();
        account.setAccount_id(1L);
        account.setUsername("TestUser");
        account.setEmail("testuser@example.com");
        account.setPassword("password123");

        accountService.saveAccount(account);

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    public void testGetAllAccounts() {
        Account account1 = new Account();
        account1.setAccount_id(1L);
        account1.setUsername("User1");
        account1.setEmail("user1@example.com");

        Account account2 = new Account();
        account2.setAccount_id(2L);
        account2.setUsername("User2");
        account2.setEmail("user2@example.com");

        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(account1);
        mockAccounts.add(account2);

        when(accountRepository.findAll()).thenReturn(mockAccounts);

        List<Account> accounts = accountService.getAllAccount();

        assertNotNull(accounts);
        assertEquals(2, accounts.size());
        assertEquals("User1", accounts.get(0).getUsername());
        assertEquals("user2@example.com", accounts.get(1).getEmail());

        verify(accountRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteAll() {
        accountService.deleteAll();

        verify(accountRepository, times(1)).deleteAll();
    }
}
