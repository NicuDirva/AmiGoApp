package com.license.AmiGo.controller;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Group;
import com.license.AmiGo.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    /**
     * API to add a new group to the system.
     *
     * @param group The group information to be added.
     * @return The created group object.
     */
    @Operation(
            summary = "Add a new group",
            description = "This endpoint allows the user to add a new group to the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid group data provided")
    })
    @PostMapping("/add")
    public Group add(
            @Parameter(description = "Group object that needs to be added", required = true)
            @RequestBody Group group
    ) {
        return groupService.saveGroup(group);
    }

    /**
     * API to get all groups in the system.
     *
     * @return A list of all groups stored in the system.
     */
    @Operation(
            summary = "Get all groups",
            description = "This endpoint retrieves a list of all groups currently stored in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all groups retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No groups found")
    })
    @GetMapping("/getAll")
    public List<Group> getAllGroup() {
        return groupService.getAllGroup();
    }

    /**
     * API to get groups by creator ID.
     *
     * @param creatorId The ID of the creator whose groups are being fetched.
     * @return A list of groups created by the specified creator.
     */
    @Operation(
            summary = "Get groups by creator ID",
            description = "This endpoint retrieves a list of groups created by a specific creator."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of groups created by the creator retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No groups found for the provided creator ID")
    })
    @PostMapping("/getGroupByCreatorId")
    public List<Group> getGroupByCreatorId(
            @Parameter(description = "The creator's ID to filter groups by", required = true)
            @RequestBody Long creatorId
    ) {
        return groupService.getGroupByCreatorId(creatorId);
    }

    /**
     * API to get members of a group by group ID.
     *
     * @param groupId The ID of the group whose members are being fetched.
     * @return A list of accounts that are members of the specified group.
     */
    @Operation(
            summary = "Get members by group ID",
            description = "This endpoint retrieves a list of all members of a specific group based on the group ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of members in the group retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No members found for the provided group ID")
    })
    @PostMapping("/getMembersByGroupId")
    public List<Account> getMembersByGroupId(
            @Parameter(description = "The group's ID to fetch members from", required = true)
            @RequestBody Long groupId
    ) {
        return groupService.getMembersByGroupId(groupId);
    }

    /**
     * API to edit group information.
     *
     * @param group The updated group information to be saved.
     */
    @Operation(
            summary = "Edit group",
            description = "This endpoint allows the user to edit the details of an existing group."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Group updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid group data provided for updating")
    })
    @PatchMapping("/editGroup")
    public void editGroup(
            @Parameter(description = "Group object with updated information", required = true)
            @RequestBody Group group
    ) {
        groupService.editGroup(group);
    }
}
