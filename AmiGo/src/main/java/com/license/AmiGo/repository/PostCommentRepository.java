package com.license.AmiGo.repository;

import com.license.AmiGo.model.Post;
import com.license.AmiGo.model.Post_comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostCommentRepository extends Neo4jRepository<Post_comment, Long> {
    @Query("MATCH (p:Post_comment)\n" +
            "WHERE id(p) = $post_id\n" +
            "DETACH DELETE p")
    void deleteComment(@Param("post_id") long post_id);
}
