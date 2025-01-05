package com.license.AmiGo.service;

import com.license.AmiGo.enums.Gender;
import com.license.AmiGo.model.Profile;
import com.license.AmiGo.repository.ProfileRepository;
import com.license.AmiGo.service.implement.ProfileServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfileServiceImplTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProfile() {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setAccount_id(101L);
        profile.setDescription("Test Description");
        profile.setImg_url("http://example.com/avatar.jpg");
        profile.setGender(Gender.MALE);
        profile.setAccess("Public");
        profile.setLocation("Test Location");

        when(profileRepository.save(profile)).thenReturn(profile);

        profileService.saveProfile(profile);

        verify(profileRepository, times(1)).save(profile);
    }

    @Test
    public void testEditDescription() {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setDescription("Updated Description");

        Profile existingProfile = new Profile();
        existingProfile.setProfile_id(1L);
        existingProfile.setDescription("Old Description");

        when(profileRepository.findById(1L)).thenReturn(Optional.of(existingProfile));

        profileService.editDescription(profile);

        assertEquals("Updated Description", existingProfile.getDescription());
        verify(profileRepository, times(1)).save(existingProfile);
    }

    @Test
    public void testEditAvatar() {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setImg_url("http://example.com/new-avatar.jpg");

        Profile existingProfile = new Profile();
        existingProfile.setProfile_id(1L);
        existingProfile.setImg_url("http://example.com/old-avatar.jpg");

        when(profileRepository.findById(1L)).thenReturn(Optional.of(existingProfile));

        profileService.editAvatar(profile);

        assertEquals("http://example.com/new-avatar.jpg", existingProfile.getImg_url());
        verify(profileRepository, times(1)).save(existingProfile);
    }

    @Test
    public void testEditGender() {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setGender(Gender.FEMALE);

        Profile existingProfile = new Profile();
        existingProfile.setProfile_id(1L);
        existingProfile.setGender(Gender.MALE);

        when(profileRepository.findById(1L)).thenReturn(Optional.of(existingProfile));

        profileService.editGender(profile);

        assertEquals(Gender.FEMALE, existingProfile.getGender());
        verify(profileRepository, times(1)).save(existingProfile);
    }

    @Test
    public void testEditAccess() {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setAccess("Private");

        Profile existingProfile = new Profile();
        existingProfile.setProfile_id(1L);
        existingProfile.setAccess("Public");

        when(profileRepository.findById(1L)).thenReturn(Optional.of(existingProfile));

        profileService.editAccess(profile);

        assertEquals("Private", existingProfile.getAccess());
        verify(profileRepository, times(1)).save(existingProfile);
    }

    @Test
    public void testGetAllProfile() {
        Profile profile1 = new Profile();
        profile1.setProfile_id(1L);
        profile1.setDescription("Profile 1");

        Profile profile2 = new Profile();
        profile2.setProfile_id(2L);
        profile2.setDescription("Profile 2");

        List<Profile> mockProfiles = new ArrayList<>();
        mockProfiles.add(profile1);
        mockProfiles.add(profile2);

        when(profileRepository.findAll()).thenReturn(mockProfiles);

        List<Profile> profiles = profileService.getAllProfile();

        assertNotNull(profiles);
        assertEquals(2, profiles.size());
        verify(profileRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteProfileByAccountId() {
        long accountId = 101L;

        doNothing().when(profileRepository).deleteProfileByAccountId(accountId);

        profileService.deleteProfileByAccountId(accountId);

        verify(profileRepository, times(1)).deleteProfileByAccountId(accountId);
    }
}
