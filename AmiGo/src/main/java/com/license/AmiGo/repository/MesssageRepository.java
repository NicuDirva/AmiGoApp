package com.license.AmiGo.repository;

import com.license.AmiGo.model.Message;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MesssageRepository extends Neo4jRepository<Message, Long> {
    @Query("MATCH (m:Message)\n" +
            "WHERE m.sender_id = $sender_id\n" +
            "RETURN m")
    List<Message> getSentMessageById(@Param("sender_id") long sender_id);
    @Query("MATCH (m:Message)\n" +
            "WHERE m.receiver_id = $receiver_id\n" +
            "RETURN m")
    List<Message> getReceivedMessageById(@Param("receiver_id") long receiver_id);

    @Query("MATCH(m:Message)\n" +
            "WHERE m.sender_id = $account_id OR m.receiver_id = $account_id\n" +
            "DETACH DELETE m")
    void deleteAllMessagesByAccountId(@Param("account_id") long account_id);
}
