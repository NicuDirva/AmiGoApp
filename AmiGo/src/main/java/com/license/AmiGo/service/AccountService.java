package com.license.AmiGo.service;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Message;
import com.license.AmiGo.model.Profile;

import java.util.List;

public interface AccountService {
    void saveAccount(Account account);
    List<Account> getAllAccount();
    void deleteAll();

}
