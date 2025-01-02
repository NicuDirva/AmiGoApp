package com.license.AmiGo.repository;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Membership;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MembershipRepository extends Neo4jRepository<Membership, Long> {
    @Query("MATCH (m:Membership)\n" +
            "WHERE m.account_id = $account_id\n" +
            "RETURN m")
    List<Membership> findByAccountId(@Param("account_id") long account_id);
    @Query("MATCH (m:Membership)\n" +
            "WHERE m.group_id = $group_id\n" +
            "RETURN m")
    List<Membership> findByGroupId(@Param("group_id") long group_id);
}
