package com.license.AmiGo.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Membership {
    @Id
    @GeneratedValue
    private long membership_id;
    private long account_id;
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
}
