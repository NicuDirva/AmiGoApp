package com.license.AmiGo.service.implement;

import com.license.AmiGo.model.Post_comment;
import com.license.AmiGo.repository.PostCommentRepository;
import com.license.AmiGo.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostCommentServiceImpl implements PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;

    @Override
    public void savePostComment(Post_comment postComment) {
        postCommentRepository.save(postComment);
    }
    @Override
    public List<Post_comment> getAllPostComment() {
        return postCommentRepository.findAll();
    }
    public void deleteComment(long comment_id) {
        postCommentRepository.deleteComment(comment_id);
    }
}
