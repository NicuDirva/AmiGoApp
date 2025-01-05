package com.license.AmiGo.model;

import jakarta.validation.constraints.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Account {
    @Id @GeneratedValue@NotNull
    private long account_id;
    @NotNull
    private String account_date_created;
    @NotNull
    private String password;
    @NotNull
    private String username;

    @Email(message = "Invalid email address")
    private String email;
    public Account() {
    }
    public Account(String account_date_created, String password, String username, String email) {
        this.account_date_created = account_date_created;
        this.password = password;
        this.username = username;
        this.email = email;
    }
    public long getAccount_id() {
        return account_id;
    }
    public void setAccount_date_created(String account_date_created) {
        this.account_date_created = account_date_created;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public String getUsername() {
        return username;
    }
}
