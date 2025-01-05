package com.license.AmiGo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AmiGo.model.Post;
import com.license.AmiGo.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddPost() throws Exception {
        Post post = new Post();
        post.setPost_id(1L);
        post.setAccount_id(1L);
        post.setGroup_id(2L);
        post.setUrlImgPost("http://example.com/image.jpg");
        post.setContentPost("This is a post");
        post.setPost_date_created("2025-01-01T12:00:00");

        mockMvc.perform(post("/post/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk());

        verify(postService, times(1)).savePost(any(Post.class));
    }

    @Test
    public void testGetAllPosts() throws Exception {
        Post post1 = new Post();
        post1.setPost_id(1L);
        post1.setAccount_id(1L);
        post1.setContentPost("First post");
        post1.setPost_date_created("2025-01-01T12:00:00");
        post1.setGroup_id(2L);
        post1.setUrlImgPost("http://example.com/image1.jpg");

        Post post2 = new Post();
        post2.setPost_id(2L);
        post2.setAccount_id(2L);
        post2.setContentPost("Second post");
        post2.setPost_date_created("2025-01-02T13:00:00");
        post2.setGroup_id(2L);
        post2.setUrlImgPost("http://example.com/image2.jpg");

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);

        when(postService.getAllPost()).thenReturn(posts);

        mockMvc.perform(get("/post/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].contentPost").value("First post"))
                .andExpect(jsonPath("$[1].contentPost").value("Second post"));
    }

    @Test
    public void testGetAccountPost() throws Exception {
        Long accountId = 1L;

        Post post1 = new Post();
        post1.setPost_id(1L);
        post1.setAccount_id(accountId);
        post1.setContentPost("Account post 1");
        post1.setPost_date_created("2025-01-01T12:00:00");
        post1.setGroup_id(2L);
        post1.setUrlImgPost("http://example.com/image1.jpg");

        Post post2 = new Post();
        post2.setPost_id(2L);
        post2.setAccount_id(accountId);
        post2.setContentPost("Account post 2");
        post2.setPost_date_created("2025-01-02T13:00:00");
        post2.setGroup_id(2L);
        post2.setUrlImgPost("http://example.com/image2.jpg");

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);

        when(postService.getAccountPost(accountId)).thenReturn(posts);

        mockMvc.perform(get("/post/getAccountPost")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].contentPost").value("Account post 1"))
                .andExpect(jsonPath("$[1].contentPost").value("Account post 2"));
    }

    @Test
    public void testGetPostsByGroupId() throws Exception {
        Long groupId = 2L;

        Post post1 = new Post();
        post1.setPost_id(1L);
        post1.setGroup_id(groupId);
        post1.setContentPost("Group post 1");

        Post post2 = new Post();
        post2.setPost_id(2L);
        post2.setGroup_id(groupId);
        post2.setContentPost("Group post 2");

        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);

        when(postService.getGroupPost(groupId)).thenReturn(posts);

        mockMvc.perform(get("/post/getPostsByGroupId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupId)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].contentPost").value("Group post 1"))
                .andExpect(jsonPath("$[1].contentPost").value("Group post 2"));
    }

    @Test
    public void testDeletePost() throws Exception {
        Long postId = 1L;

        mockMvc.perform(post("/post/DELETE_POST")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postId)))
                .andExpect(status().isOk());

        verify(postService, times(1)).deletePost(postId);
    }
}