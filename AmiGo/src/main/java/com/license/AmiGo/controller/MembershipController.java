package com.license.AmiGo.controller;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Membership;
import com.license.AmiGo.service.MembershipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    /**
     * API to add a new membership to the system.
     *
     * @param membership The membership details to be added.
     */
    @Operation(
            summary = "Add a new membership",
            description = "This endpoint allows the user to add a new membership to the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Membership added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid membership data provided")
    })
    @PostMapping("/add")
    public void add(
            @Parameter(description = "Membership object to be added", required = true)
            @RequestBody Membership membership
    ) {
        membershipService.saveMembership(membership);
    }

    /**
     * API to get all memberships in the system.
     *
     * @return A list of all memberships in the system.
     */
    @Operation(
            summary = "Get all memberships",
            description = "This endpoint retrieves a list of all memberships currently stored in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all memberships retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No memberships found")
    })
    @GetMapping("/getAll")
    public List<Membership> getAllMembership() {
        return membershipService.getAllMembership();
    }

    /**
     * API to get memberships by account ID.
     *
     * @param accountId The ID of the account whose memberships are to be retrieved.
     * @return A list of memberships associated with the specified account.
     */
    @Operation(
            summary = "Get memberships by account ID",
            description = "This endpoint retrieves a list of memberships associated with a specific account."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of memberships for the account retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No memberships found for the provided account ID")
    })
    @GetMapping("/getMembershipByAccountId")
    public List<Membership> getMembershipByAccountId(
            @Parameter(description = "The account ID to filter memberships by", required = true)
            @RequestBody Long accountId
    ) {
        return membershipService.getMembershipByAccountId(accountId);
    }

    /**
     * API to get memberships by group ID.
     *
     * @param groupId The ID of the group whose memberships are to be retrieved.
     * @return A list of memberships associated with the specified group.
     */
    @Operation(
            summary = "Get memberships by group ID",
            description = "This endpoint retrieves a list of memberships associated with a specific group."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of memberships for the group retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No memberships found for the provided group ID")
    })
    @GetMapping("/getMembershipByGroupId")
    public List<Membership> getMembershipByGroupId(
            @Parameter(description = "The group ID to filter memberships by", required = true)
            @RequestBody Long groupId
    ) {
        return membershipService.getMembershipByGroupId(groupId);
    }

    /**
     * API to delete a membership by its ID.
     *
     * @param membershipId The ID of the membership to be deleted.
     */
    @Operation(
            summary = "Delete membership by ID",
            description = "This endpoint allows the user to delete a specific membership by its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Membership deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Membership not found for the provided ID")
    })
    @PatchMapping("/deleteMembershipById")
    public void deleteMembershipById(
            @Parameter(description = "The membership ID to delete", required = true)
            @RequestBody Long membershipId
    ) {
        membershipService.deleteMembershipById(membershipId);
    }
}
