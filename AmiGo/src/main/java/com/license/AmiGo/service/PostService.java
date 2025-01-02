package com.license.AmiGo.service;

import com.license.AmiGo.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    void savePost(Post post);
    List<Post> getAllPost();
    List<Post> getAccountPost(long account_id);
    List<Post> getGroupPost(long group_id);
    void deletePost(long post_id);
}
