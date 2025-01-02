package com.license.AmiGo.controller;

import com.license.AmiGo.model.Profile;
import com.license.AmiGo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/add")
    public void add(@RequestBody Profile profile) {
        profileService.saveProfile(profile);
    }
    @GetMapping("/getAll")
    public List<Profile> getAllProfile() {
        return profileService.getAllProfile();
    }
    @PostMapping("/editDescription")
    public void editDescription(@RequestBody Profile profile) {
        profileService.editDescription(profile);
    }
    @PostMapping("/editAvatar")
    public void editAvatar(@RequestBody Profile profile) {
        profileService.editAvatar(profile);
    }
    @PostMapping("/editGender")
    public void editGender(@RequestBody Profile profile) {
        profileService.editGender(profile);
    }
    @PostMapping("/editAccess")
    public void editAccess(@RequestBody Profile profile) {
        profileService.editAccess(profile);
    }
    @PatchMapping("/deleteProfileByAccountId")
    void deleteProfileByAccountId(@RequestBody Long account_id) {
        profileService.deleteProfileByAccountId(account_id);
    }
}