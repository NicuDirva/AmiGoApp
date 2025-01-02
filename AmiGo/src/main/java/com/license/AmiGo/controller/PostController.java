package com.license.AmiGo.controller;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Post;
import com.license.AmiGo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/add")
    public void add(@RequestBody Post post) {
        postService.savePost(post);
    }
    @GetMapping("/getAll")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }
    @GetMapping("/getAccountPost")
    public List<Post> getAccountPost(@RequestBody Long account_id) {
        return postService.getAccountPost(account_id);
    }
    @GetMapping("/getPostsByGroupId")
    public List<Post> getGroupPost(@RequestBody Long group_id) {
        return postService.getGroupPost(group_id);
    }
    @PostMapping("/DELETE_POST")
    public void deletePost(@RequestBody Long post_id) {
        postService.deletePost(post_id);
    }
}
