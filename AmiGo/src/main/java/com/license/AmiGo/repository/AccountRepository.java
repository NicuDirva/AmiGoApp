package com.license.AmiGo.repository;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Group;
import com.license.AmiGo.model.Post;
import com.license.AmiGo.model.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends Neo4jRepository<Account, Long> {
    @Query("MATCH (a:Account)\n" +
            "WHERE id(a) = $account_id\n" +
            "DETACH DELETE a")
    void deleteAccountById(@Param("account_id") long account_id);
}
