package com.license.AmiGo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AmiGo.enums.Gender;
import com.license.AmiGo.model.Profile;
import com.license.AmiGo.service.ProfileService;
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
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddProfile() throws Exception {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setAccount_id(1L);
        profile.setImg_url("http://example.com/avatar.jpg");
        profile.setDescription("This is a test description");
        profile.setGender(Gender.MALE);
        profile.setDob("1990-01-01");
        profile.setAccess("Public");
        profile.setLocation("New York");

        mockMvc.perform(post("/profile/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isOk());

        verify(profileService, times(1)).saveProfile(any(Profile.class));
    }

    @Test
    public void testGetAllProfiles() throws Exception {
        Profile profile1 = new Profile();
        profile1.setProfile_id(1L);
        profile1.setAccount_id(1L);
        profile1.setDescription("Description 1");

        Profile profile2 = new Profile();
        profile2.setProfile_id(2L);
        profile2.setAccount_id(2L);
        profile2.setDescription("Description 2");

        List<Profile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);

        when(profileService.getAllProfile()).thenReturn(profiles);

        mockMvc.perform(get("/profile/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].description").value("Description 1"))
                .andExpect(jsonPath("$[1].description").value("Description 2"));
    }

    @Test
    public void testEditDescription() throws Exception {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setDescription("Updated description");

        mockMvc.perform(post("/profile/editDescription")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isOk());

        verify(profileService, times(1)).editDescription(any(Profile.class));
    }

    @Test
    public void testEditAvatar() throws Exception {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setImg_url("http://example.com/updated-avatar.jpg");

        mockMvc.perform(post("/profile/editAvatar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isOk());

        verify(profileService, times(1)).editAvatar(any(Profile.class));
    }

    @Test
    public void testEditGender() throws Exception {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setGender(Gender.FEMALE);

        mockMvc.perform(post("/profile/editGender")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isOk());

        verify(profileService, times(1)).editGender(any(Profile.class));
    }

    @Test
    public void testEditAccess() throws Exception {
        Profile profile = new Profile();
        profile.setProfile_id(1L);
        profile.setAccess("Private");

        mockMvc.perform(post("/profile/editAccess")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isOk());

        verify(profileService, times(1)).editAccess(any(Profile.class));
    }

    @Test
    public void testDeleteProfileByAccountId() throws Exception {
        Long accountId = 1L;

        mockMvc.perform(patch("/profile/deleteProfileByAccountId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(accountId)))
                .andExpect(status().isOk());

        verify(profileService, times(1)).deleteProfileByAccountId(accountId);
    }
}