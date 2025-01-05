package com.license.AmiGo.controller;

import com.license.AmiGo.model.Post_comment;
import com.license.AmiGo.service.PostCommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class PostCommentController {

    @Autowired
    private PostCommentService postCommentService;

    /**
     * API to add a new comment to a post.
     *
     * @param postComment The comment details to be added.
     */
    @Operation(
            summary = "Add a new post comment",
            description = "This endpoint allows the user to add a new comment to a specific post."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid comment data provided")
    })
    @PostMapping("/add")
    public void add(
            @Parameter(description = "Post comment object that needs to be added", required = true)
            @RequestBody Post_comment postComment
    ) {
        postCommentService.savePostComment(postComment);
    }

    /**
     * API to get all comments from the system.
     *
     * @return A list of all post comments stored in the system.
     */
    @Operation(
            summary = "Get all post comments",
            description = "This endpoint retrieves a list of all comments currently stored in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all post comments retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No comments found")
    })
    @GetMapping("/getAll")
    public List<Post_comment> getAllPostComment() {
        return postCommentService.getAllPostComment();
    }

    /**
     * API to delete a specific comment by comment ID.
     *
     * @param commentId The ID of the comment to be deleted.
     */
    @Operation(
            summary = "Delete a comment by ID",
            description = "This endpoint allows the user to delete a specific comment using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found for the provided ID")
    })
    @PostMapping("/DELETE_COMMENT")
    public void deleteComment(
            @Parameter(description = "The comment ID to delete", required = true)
            @RequestBody Long commentId
    ) {
        postCommentService.deleteComment(commentId);
    }
}