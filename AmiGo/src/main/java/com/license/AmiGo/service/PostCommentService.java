package com.license.AmiGo.service;

import com.license.AmiGo.model.Post_comment;

import java.util.List;

public interface PostCommentService {
    void savePostComment(Post_comment postComment);
    List<Post_comment> getAllPostComment();
    void deleteComment(long comment_id);
}
