package com.license.AmiGo.service;

import com.license.AmiGo.model.Post_comment;
import com.license.AmiGo.repository.PostCommentRepository;
import com.license.AmiGo.service.implement.PostCommentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostCommentServiceImplTest {

    @Mock
    private PostCommentRepository postCommentRepository;

    @InjectMocks
    private PostCommentServiceImpl postCommentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePostComment() {
        Post_comment postComment = new Post_comment();
        postComment.setComment_id(1L);
        postComment.setAccount_id(101L);
        postComment.setPost_id(201L);
        postComment.setContent("This is a test comment");
        postComment.setCreated_date("2023-01-01");

        when(postCommentRepository.save(postComment)).thenReturn(postComment);

        postCommentService.savePostComment(postComment);

        verify(postCommentRepository, times(1)).save(postComment);
    }

    @Test
    public void testGetAllPostComment() {
        Post_comment comment1 = new Post_comment();
        comment1.setComment_id(1L);
        comment1.setAccount_id(101L);
        comment1.setPost_id(201L);
        comment1.setContent("Comment 1");
        comment1.setCreated_date("2023-01-01");

        Post_comment comment2 = new Post_comment();
        comment2.setComment_id(2L);
        comment2.setAccount_id(102L);
        comment2.setPost_id(202L);
        comment2.setContent("Comment 2");
        comment2.setCreated_date("2023-01-02");

        List<Post_comment> mockComments = new ArrayList<>();
        mockComments.add(comment1);
        mockComments.add(comment2);

        when(postCommentRepository.findAll()).thenReturn(mockComments);

        List<Post_comment> comments = postCommentService.getAllPostComment();

        assertNotNull(comments);
        assertEquals(2, comments.size());
        assertEquals("Comment 1", comments.get(0).getContent());
        verify(postCommentRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteComment() {
        long commentId = 1L;

        doNothing().when(postCommentRepository).deleteComment(commentId);

        postCommentService.deleteComment(commentId);

        verify(postCommentRepository, times(1)).deleteComment(commentId);
    }
}

