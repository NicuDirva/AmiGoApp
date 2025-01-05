package com.license.AmiGo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AmiGo.model.Account;
import com.license.AmiGo.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddAccount() throws Exception {
        Account account = new Account();
        account.setAccount_id(1L);
        account.setUsername("TestUser");
        account.setEmail("testuser@example.com");
        account.setPassword("password123");

        mockMvc.perform(post("/account/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(account)))
                .andExpect(status().isOk());

        verify(accountService, times(1)).saveAccount(any(Account.class));
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        mockMvc.perform(delete("/account/deleteAll"))
                .andExpect(status().isOk());

        Account account1 = new Account();
        account1.setUsername("User1");
        account1.setEmail("user1@example.com");
        account1.setPassword("password123");

        Account account2 = new Account();
        account2.setUsername("User2");
        account2.setEmail("user2@example.com");
        account2.setPassword("password456");

        List<Account> accounts = new ArrayList<>();
        accounts.add(account1);
        accounts.add(account2);

        when(accountService.getAllAccount()).thenReturn(accounts);

        mockMvc.perform(post("/account/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(account1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/account/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(account2)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/account/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[1].email").value("user2@example.com"));

        mockMvc.perform(delete("/account/deleteAll"))
                .andExpect(status().isOk());
    }
}

