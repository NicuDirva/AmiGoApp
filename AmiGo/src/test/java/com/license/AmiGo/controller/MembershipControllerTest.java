package com.license.AmiGo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AmiGo.model.Membership;
import com.license.AmiGo.service.MembershipService;
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
public class MembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MembershipService membershipService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddMembership() throws Exception {
        Membership membership = new Membership();
        membership.setMembership_id(1L);
        membership.setAccount_id(1L);
        membership.setGroup_id(1L);

        mockMvc.perform(post("/membership/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(membership)))
                .andExpect(status().isOk());

        verify(membershipService, times(1)).saveMembership(any(Membership.class));
    }

    @Test
    public void testGetAllMemberships() throws Exception {
        Membership membership1 = new Membership();
        membership1.setMembership_id(1L);
        membership1.setAccount_id(1L);
        membership1.setGroup_id(1L);

        Membership membership2 = new Membership();
        membership2.setMembership_id(2L);
        membership2.setAccount_id(2L);
        membership2.setGroup_id(2L);

        List<Membership> memberships = new ArrayList<>();
        memberships.add(membership1);
        memberships.add(membership2);

        when(membershipService.getAllMembership()).thenReturn(memberships);

        mockMvc.perform(post("/membership/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(membership1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/membership/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(membership2)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/membership/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].account_id").value(1L))
                .andExpect(jsonPath("$[1].account_id").value(2L));
    }

    @Test
    public void testGetMembershipByAccountId() throws Exception {
        Membership membership1 = new Membership();
        membership1.setMembership_id(1L);
        membership1.setAccount_id(1L);
        membership1.setGroup_id(1L);

        Membership membership2 = new Membership();
        membership2.setMembership_id(2L);
        membership2.setAccount_id(1L);
        membership2.setGroup_id(2L);

        List<Membership> memberships = new ArrayList<>();
        memberships.add(membership1);
        memberships.add(membership2);

        when(membershipService.getMembershipByAccountId(1L)).thenReturn(memberships);

        mockMvc.perform(get("/membership/getMembershipByAccountId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].group_id").value(1L))
                .andExpect(jsonPath("$[1].group_id").value(2L));
    }

    @Test
    public void testGetMembershipByGroupId() throws Exception {
        Membership membership1 = new Membership();
        membership1.setMembership_id(1L);
        membership1.setAccount_id(1L);
        membership1.setGroup_id(1L);

        Membership membership2 = new Membership();
        membership2.setMembership_id(2L);
        membership2.setAccount_id(2L);
        membership2.setGroup_id(1L);

        List<Membership> memberships = new ArrayList<>();
        memberships.add(membership1);
        memberships.add(membership2);

        when(membershipService.getMembershipByGroupId(1L)).thenReturn(memberships);

        mockMvc.perform(get("/membership/getMembershipByGroupId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].account_id").value(1L))
                .andExpect(jsonPath("$[1].account_id").value(2L));
    }

    @Test
    public void testDeleteMembershipById() throws Exception {
        Long membershipId = 1L;

        mockMvc.perform(patch("/membership/deleteMembershipById")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(membershipId)))
                .andExpect(status().isOk());

        verify(membershipService, times(1)).deleteMembershipById(any(Long.class));
    }
}