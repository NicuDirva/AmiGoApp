package com.license.AmiGo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Group;
import com.license.AmiGo.service.GroupService;
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
public class GroupControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddGroup() throws Exception {
        Group group = new Group();
        group.setGroup_id(1L);
        group.setCreator_id(1L);
        group.setName("Test Group");
        group.setAccess("Public");
        group.setUrlImg("http://example.com/img.png");

        mockMvc.perform(post("/group/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(group)))
                .andExpect(status().isOk());

        verify(groupService, times(1)).saveGroup(any(Group.class));
    }

    @Test
    public void testGetAllGroups() throws Exception {
        Group group1 = new Group();
        group1.setGroup_id(1L);
        group1.setCreator_id(1L);
        group1.setName("Group 1");
        group1.setAccess("Public");
        group1.setUrlImg("http://example.com/group1.png");

        Group group2 = new Group();
        group2.setGroup_id(2L);
        group2.setCreator_id(2L);
        group2.setName("Group 2");
        group2.setAccess("Private");
        group2.setUrlImg("http://example.com/group2.png");

        List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);

        when(groupService.getAllGroup()).thenReturn(groups);

        mockMvc.perform(post("/group/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(group1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/group/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(group2)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/group/getAll")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Group 1"))
                .andExpect(jsonPath("$[1].name").value("Group 2"));
    }

    @Test
    public void testGetGroupByCreatorId() throws Exception {
        Group group1 = new Group();
        group1.setGroup_id(1L);
        group1.setCreator_id(1L);
        group1.setName("Group 1");
        group1.setAccess("Public");
        group1.setUrlImg("http://example.com/group1.png");

        Group group2 = new Group();
        group2.setGroup_id(2L);
        group2.setCreator_id(1L);
        group2.setName("Group 2");
        group2.setAccess("Private");
        group2.setUrlImg("http://example.com/group2.png");

        List<Group> groups = new ArrayList<>();
        groups.add(group1);
        groups.add(group2);

        when(groupService.getGroupByCreatorId(1L)).thenReturn(groups);

        mockMvc.perform(post("/group/getGroupByCreatorId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Group 1"))
                .andExpect(jsonPath("$[1].name").value("Group 2"));
    }

    @Test
    public void testGetMembersByGroupId() throws Exception {
        Group group1 = new Group();
        group1.setGroup_id(1L);
        group1.setCreator_id(1L);
        group1.setName("Group 1");
        group1.setAccess("Public");
        group1.setUrlImg("http://example.com/group1.png");

        Account account1 = new Account();
        account1.setAccount_id(1L);
        account1.setUsername("User1");
        account1.setEmail("user1@example.com");

        List<Account> members = new ArrayList<>();
        members.add(account1);

        when(groupService.getMembersByGroupId(1L)).thenReturn(members);

        mockMvc.perform(post("/group/getMembersByGroupId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"));
    }

    @Test
    public void testEditGroup() throws Exception {
        Group group = new Group();
        group.setGroup_id(1L);
        group.setCreator_id(1L);
        group.setName("Updated Group");
        group.setAccess("Private");
        group.setUrlImg("http://example.com/updated_group.png");

        mockMvc.perform(patch("/group/editGroup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(group)))
                .andExpect(status().isOk());

        verify(groupService, times(1)).editGroup(any(Group.class));
    }
}