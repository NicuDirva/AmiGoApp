package com.license.AmiGo.controller;

import com.license.AmiGo.model.Post;
import com.license.AmiGo.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * API to add a new post.
     *
     * @param post The post details to be added.
     */
    @Operation(
            summary = "Add a new post",
            description = "This endpoint allows the user to add a new post to the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid post data provided")
    })
    @PostMapping("/add")
    public void add(
            @Parameter(description = "Post object to be added", required = true)
            @RequestBody Post post
    ) {
        postService.savePost(post);
    }

    /**
     * API to get all posts.
     *
     * @return A list of all posts in the system.
     */
    @Operation(
            summary = "Get all posts",
            description = "This endpoint retrieves a list of all posts in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all posts retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No posts found")
    })
    @GetMapping("/getAll")
    public List<Post> getAllPost() {
        return postService.getAllPost();
    }

    /**
     * API to get posts by account ID.
     *
     * @param accountId The ID of the account whose posts are to be retrieved.
     * @return A list of posts associated with the specified account.
     */
    @Operation(
            summary = "Get posts by account ID",
            description = "This endpoint retrieves a list of posts created by a specific account."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of posts for the account retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No posts found for the provided account ID")
    })
    @GetMapping("/getAccountPost")
    public List<Post> getAccountPost(
            @Parameter(description = "The account ID to filter posts by", required = true)
            @RequestBody Long accountId
    ) {
        return postService.getAccountPost(accountId);
    }

    /**
     * API to get posts by group ID.
     *
     * @param groupId The ID of the group whose posts are to be retrieved.
     * @return A list of posts associated with the specified group.
     */
    @Operation(
            summary = "Get posts by group ID",
            description = "This endpoint retrieves a list of posts associated with a specific group."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of posts for the group retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No posts found for the provided group ID")
    })
    @GetMapping("/getPostsByGroupId")
    public List<Post> getGroupPost(
            @Parameter(description = "The group ID to filter posts by", required = true)
            @RequestBody Long groupId
    ) {
        return postService.getGroupPost(groupId);
    }

    /**
     * API to delete a post by post ID.
     *
     * @param postId The ID of the post to be deleted.
     */
    @Operation(
            summary = "Delete a post by ID",
            description = "This endpoint allows the user to delete a specific post using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found for the provided ID")
    })
    @PostMapping("/DELETE_POST")
    public void deletePost(
            @Parameter(description = "The post ID to delete", required = true)
            @RequestBody Long postId
    ) {
        postService.deletePost(postId);
    }
}