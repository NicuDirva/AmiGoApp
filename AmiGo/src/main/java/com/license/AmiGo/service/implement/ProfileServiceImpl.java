package com.license.AmiGo.service.implement;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Profile;
import com.license.AmiGo.repository.AccountRepository;
import com.license.AmiGo.repository.ProfileRepository;
import com.license.AmiGo.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void saveProfile(Profile profile) {
        profileRepository.save(profile);
    }
    public void editDescription(Profile profile) {
        Profile existingProfile = profileRepository.findById(profile.getProfile_id())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setDescription(profile.getDescription());

        profileRepository.save(existingProfile);
    }
    @Override
    public void editAvatar(Profile profile) {
        Profile existingProfile = profileRepository.findById(profile.getProfile_id())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setImg_url(profile.getImg_url());

        profileRepository.save(existingProfile);
    }
    public void editGender(Profile profile) {
        Profile existingProfile = profileRepository.findById(profile.getProfile_id())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setGender(profile.getGender());

        profileRepository.save(existingProfile);
    }
    public void editAccess(Profile profile) {
        Profile existingProfile = profileRepository.findById(profile.getProfile_id())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingProfile.setAccess(profile.getAccess());

        profileRepository.save(existingProfile);
    }
    @Override
    public List<Profile> getAllProfile() {
        return profileRepository.findAll();
    }
    public void deleteProfileByAccountId(long account_id) {
        profileRepository.deleteProfileByAccountId(account_id);
    }
}
