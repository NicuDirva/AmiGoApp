package com.license.AmiGo.repository;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Group;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface GroupRepository extends Neo4jRepository<Group, Long> {

    @Query("MATCH (g:Group)\n" +
            "WHERE g.creator_id = $creator_id\n" +
            "RETURN g")
    List<Group> getGroupByCreatorId(@Param("creator_id") long creator_id);
    @Query("MATCH (a:Account)\n" +
            "MATCH (m:Membership)\n" +
            "WHERE m.group_id = $group_id AND m.account_id = id(a)\n" +
            "RETURN id(a) as account_id, a.account_date_created as account_date_created, a.password as password, a.username as username, a.email as email")
    List<Account> getMembersByGroupId(@Param("group_id") long group_id);
}
