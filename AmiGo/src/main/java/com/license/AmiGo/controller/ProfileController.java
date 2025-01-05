package com.license.AmiGo.controller;

import com.license.AmiGo.model.Profile;
import com.license.AmiGo.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /**
     * API to add a new profile.
     *
     * @param profile The profile details to be added.
     */
    @Operation(
            summary = "Add a new profile",
            description = "This endpoint allows the user to create a new profile in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid profile data provided")
    })
    @PostMapping("/add")
    public void add(
            @Parameter(description = "Profile object to be added", required = true)
            @RequestBody Profile profile
    ) {
        profileService.saveProfile(profile);
    }

    /**
     * API to get all profiles.
     *
     * @return A list of all profiles in the system.
     */
    @Operation(
            summary = "Get all profiles",
            description = "This endpoint retrieves a list of all profiles stored in the system."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all profiles retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "No profiles found")
    })
    @GetMapping("/getAll")
    public List<Profile> getAllProfile() {
        return profileService.getAllProfile();
    }

    /**
     * API to edit the description of an existing profile.
     *
     * @param profile The profile with updated description to be modified.
     */
    @Operation(
            summary = "Edit profile description",
            description = "This endpoint allows the user to update the description of an existing profile."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile description updated successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PostMapping("/editDescription")
    public void editDescription(
            @Parameter(description = "Profile object with updated description", required = true)
            @RequestBody Profile profile
    ) {
        profileService.editDescription(profile);
    }

    /**
     * API to edit the avatar of an existing profile.
     *
     * @param profile The profile with updated avatar to be modified.
     */
    @Operation(
            summary = "Edit profile avatar",
            description = "This endpoint allows the user to update the avatar of an existing profile."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile avatar updated successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PostMapping("/editAvatar")
    public void editAvatar(
            @Parameter(description = "Profile object with updated avatar", required = true)
            @RequestBody Profile profile
    ) {
        profileService.editAvatar(profile);
    }

    /**
     * API to edit the gender of an existing profile.
     *
     * @param profile The profile with updated gender to be modified.
     */
    @Operation(
            summary = "Edit profile gender",
            description = "This endpoint allows the user to update the gender of an existing profile."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile gender updated successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PostMapping("/editGender")
    public void editGender(
            @Parameter(description = "Profile object with updated gender", required = true)
            @RequestBody Profile profile
    ) {
        profileService.editGender(profile);
    }

    /**
     * API to edit the access status of an existing profile.
     *
     * @param profile The profile with updated access status to be modified.
     */
    @Operation(
            summary = "Edit profile access",
            description = "This endpoint allows the user to update the access status of an existing profile."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile access updated successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PostMapping("/editAccess")
    public void editAccess(
            @Parameter(description = "Profile object with updated access status", required = true)
            @RequestBody Profile profile
    ) {
        profileService.editAccess(profile);
    }

    /**
     * API to delete a profile by account ID.
     *
     * @param accountId The ID of the account whose profile is to be deleted.
     */
    @Operation(
            summary = "Delete profile by account ID",
            description = "This endpoint allows the user to delete a profile based on the account ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @PatchMapping("/deleteProfileByAccountId")
    void deleteProfileByAccountId(
            @Parameter(description = "Account ID to delete associated profile", required = true)
            @RequestBody Long accountId
    ) {
        profileService.deleteProfileByAccountId(accountId);
    }
}