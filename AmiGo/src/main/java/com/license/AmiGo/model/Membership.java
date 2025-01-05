package com.license.AmiGo.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Membership {
    @Id
    @GeneratedValue@NotNull
    private long membership_id;
    @NotNull
    private long account_id;
    @NotNull
    private long group_id;

    public Membership(long membership_id, long account_id, long group_id) {
        this.membership_id = membership_id;
        this.account_id = account_id;
        this.group_id = group_id;
    }

    public Membership() {
    }

    public long getMembership_id() {
        return membership_id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setMembership_id(long membership_id) {
        this.membership_id = membership_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }
}
