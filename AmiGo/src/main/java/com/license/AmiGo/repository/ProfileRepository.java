package com.license.AmiGo.repository;

import com.license.AmiGo.model.Profile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

public interface ProfileRepository extends Neo4jRepository<Profile, Long> {
    @Query("MATCH (p:Profile)\n" +
            "WHERE p.account_id = $account_id\n" +
            "DETACH DELETE p")
    void deleteProfileByAccountId(@Param("account_id") long account_id);
}
