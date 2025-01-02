package com.license.AmiGo.service.implement;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Post;
import com.license.AmiGo.repository.PostRepository;
import com.license.AmiGo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }
    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }
    @Override
    public List<Post> getAccountPost(long account_id) {
        return postRepository.getAllPost(account_id);
    }
    public List<Post> getGroupPost(long group_id) {
        return postRepository.getGroupPost(group_id);
    }
    public void deletePost(long post_id) {
        postRepository.deletePost(post_id);
    }
}
