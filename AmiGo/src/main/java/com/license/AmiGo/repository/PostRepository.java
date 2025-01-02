package com.license.AmiGo.repository;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Post;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends Neo4jRepository<Post, Long> {
    @Query("MATCH (a:Account)-[:HAS_POST]->(p:Post)\n" +
            "WHERE id(a) = $account_id\n" +
            "RETURN DISTINCT p")
    List<Post> getAllPost(@Param("account_id") long account_id);
    @Query("MATCH (p:Post)\n" +
            "WHERE p.group_id = $group_id\n" +
            "RETURN DISTINCT p")
    List<Post> getGroupPost(@Param("group_id") long group_id);
    @Query("MATCH (p:Post)\n" +
            "WHERE id(p) = $post_id\n" +
            "DETACH DELETE p")
    void deletePost(@Param("post_id") long post_id);
}
