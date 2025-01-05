package com.license.AmiGo.service;

import com.license.AmiGo.model.Post;
import com.license.AmiGo.repository.PostRepository;
import com.license.AmiGo.service.implement.PostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePost() {
        Post post = new Post();
        post.setPost_id(1L);
        post.setAccount_id(101L);
        post.setGroup_id(201L);
        post.setUrlImgPost("http://example.com/image.jpg");
        post.setContentPost("This is a test post.");
        post.setPost_date_created("2023-01-01");

        when(postRepository.save(post)).thenReturn(post);

        postService.savePost(post);

        verify(postRepository, times(1)).save(post);
    }

    @Test
    public void testGetAllPost() {
        Post post1 = new Post();
        post1.setPost_id(1L);
        post1.setAccount_id(101L);
        post1.setGroup_id(201L);
        post1.setContentPost("Post 1");

        Post post2 = new Post();
        post2.setPost_id(2L);
        post2.setAccount_id(102L);
        post2.setGroup_id(202L);
        post2.setContentPost("Post 2");

        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(post1);
        mockPosts.add(post2);

        when(postRepository.findAll()).thenReturn(mockPosts);

        List<Post> posts = postService.getAllPost();

        assertNotNull(posts);
        assertEquals(2, posts.size());
        assertEquals("Post 1", posts.get(0).getContentPost());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    public void testGetAccountPost() {
        long accountId = 101L;

        Post post1 = new Post();
        post1.setPost_id(1L);
        post1.setAccount_id(accountId);
        post1.setContentPost("Account Post 1");

        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(post1);

        when(postRepository.getAllPost(accountId)).thenReturn(mockPosts);

        List<Post> posts = postService.getAccountPost(accountId);

        assertNotNull(posts);
        assertEquals(1, posts.size());
        assertEquals("Account Post 1", posts.get(0).getContentPost());
        verify(postRepository, times(1)).getAllPost(accountId);
    }

    @Test
    public void testGetGroupPost() {
        long groupId = 201L;

        Post post1 = new Post();
        post1.setPost_id(1L);
        post1.setGroup_id(groupId);
        post1.setContentPost("Group Post 1");

        List<Post> mockPosts = new ArrayList<>();
        mockPosts.add(post1);

        when(postRepository.getGroupPost(groupId)).thenReturn(mockPosts);

        List<Post> posts = postService.getGroupPost(groupId);

        assertNotNull(posts);
        assertEquals(1, posts.size());
        assertEquals("Group Post 1", posts.get(0).getContentPost());
        verify(postRepository, times(1)).getGroupPost(groupId);
    }

    @Test
    public void testDeletePost() {
        long postId = 1L;

        doNothing().when(postRepository).deletePost(postId);

        postService.deletePost(postId);

        verify(postRepository, times(1)).deletePost(postId);
    }
}

