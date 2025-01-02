package com.license.AmiGo.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.util.Vector;

@Node
public class Post {
    @Id@GeneratedValue
    private long post_id;
    private long account_id;
    private Long group_id;
    private String urlImgPost;
    private String contentPost;
    private String post_date_created;

    public Post() {
    }
    public Post(long account_id, long group_id, String urlImgPost, String contentPost, String post_date_created) {
        this.account_id = account_id;
        this.group_id = group_id;
        this.urlImgPost = urlImgPost;
        this.contentPost = contentPost;
        this.post_date_created = post_date_created;
    }


    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(long account_id) {
        this.account_id = account_id;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public String getUrlImgPost() {
        return urlImgPost;
    }

    public void setUrlImgPost(String urlImgPost) {
        this.urlImgPost = urlImgPost;
    }

    public String getContentPost() {
        return contentPost;
    }

    public void setContentPost(String contentPost) {
        this.contentPost = contentPost;
    }

    public String getPost_date_created() {
        return post_date_created;
    }

    public void setPost_date_created(String post_date_created) {
        this.post_date_created = post_date_created;
    }

}
