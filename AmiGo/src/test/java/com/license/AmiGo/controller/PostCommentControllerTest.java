package com.license.AmiGo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AmiGo.model.Post_comment;
import com.license.AmiGo.service.PostCommentService;
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
public class PostCommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostCommentService postCommentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddPostComment() throws Exception {
        Post_comment postComment = new Post_comment();
        postComment.setComment_id(1L);
        postComment.setAccount_id(1L);
        postComment.setPost_id(1L);
        postComment.setContent("This is a comment");
        postComment.setCreated_date("2025-01-01T12:00:00");

        mockMvc.perform(post("/comment/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postComment)))
                .andExpect(status().isOk());

        verify(postCommentService, times(1)).savePostComment(any(Post_comment.class));
    }

    @Test
    public void testGetAllPostComments() throws Exception {
        Post_comment postComment1 = new Post_comment();
        postComment1.setComment_id(1L);
        postComment1.setAccount_id(1L);
        postComment1.setPost_id(1L);
        postComment1.setContent("First comment");
        postComment1.setCreated_date("2025-01-01T12:00:00");

        Post_comment postComment2 = new Post_comment();
        postComment2.setComment_id(2L);
        postComment2.setAccount_id(2L);
        postComment2.setPost_id(1L);
        postComment2.setContent("Second comment");
        postComment2.setCreated_date("2025-01-02T13:00:00");

        List<Post_comment> comments = new ArrayList<>();
        comments.add(postComment1);
        comments.add(postComment2);

        when(postCommentService.getAllPostComment()).thenReturn(comments);

        mockMvc.perform(get("/comment/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].content").value("First comment"))
                .andExpect(jsonPath("$[1].content").value("Second comment"));
    }

    @Test
    public void testDeleteComment() throws Exception {
        Long commentId = 1L;

        mockMvc.perform(post("/comment/DELETE_COMMENT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentId)))
                .andExpect(status().isOk());

        verify(postCommentService, times(1)).deleteComment(any(Long.class));
    }
}
