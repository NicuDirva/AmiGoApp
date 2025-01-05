package com.license.AmiGo.service;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Group;
import com.license.AmiGo.repository.GroupRepository;
import com.license.AmiGo.service.implement.GroupServiceImpl;
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

public class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveGroup() {
        Group group = new Group();
        group.setGroup_id(1L);
        group.setCreator_id(1L);
        group.setName("Test Group");
        group.setAccess("Public");
        group.setUrlImg("http://example.com/image.jpg");

        when(groupRepository.save(group)).thenReturn(group);

        Group savedGroup = groupService.saveGroup(group);

        assertNotNull(savedGroup);
        assertEquals("Test Group", savedGroup.getName());
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    public void testGetAllGroup() {
        Group group1 = new Group();
        group1.setGroup_id(1L);
        group1.setName("Group 1");

        Group group2 = new Group();
        group2.setGroup_id(2L);
        group2.setName("Group 2");

        List<Group> mockGroups = new ArrayList<>();
        mockGroups.add(group1);
        mockGroups.add(group2);

        when(groupRepository.findAll()).thenReturn(mockGroups);

        List<Group> groups = groupService.getAllGroup();

        assertNotNull(groups);
        assertEquals(2, groups.size());
        assertEquals("Group 1", groups.get(0).getName());
        assertEquals("Group 2", groups.get(1).getName());

        verify(groupRepository, times(1)).findAll();
    }

    @Test
    public void testGetGroupByCreatorId() {
        long creatorId = 1L;

        Group group = new Group();
        group.setGroup_id(1L);
        group.setCreator_id(creatorId);
        group.setName("Creator Group");

        List<Group> mockGroups = new ArrayList<>();
        mockGroups.add(group);

        when(groupRepository.getGroupByCreatorId(creatorId)).thenReturn(mockGroups);

        List<Group> groups = groupService.getGroupByCreatorId(creatorId);

        assertNotNull(groups);
        assertEquals(1, groups.size());
        assertEquals("Creator Group", groups.get(0).getName());

        verify(groupRepository, times(1)).getGroupByCreatorId(creatorId);
    }

    @Test
    public void testGetMembersByGroupId() {
        long groupId = 1L;

        Account account1 = new Account();
        account1.setAccount_id(1L);
        account1.setUsername("User1");

        Account account2 = new Account();
        account2.setAccount_id(2L);
        account2.setUsername("User2");

        List<Account> mockMembers = new ArrayList<>();
        mockMembers.add(account1);
        mockMembers.add(account2);

        when(groupRepository.getMembersByGroupId(groupId)).thenReturn(mockMembers);

        List<Account> members = groupService.getMembersByGroupId(groupId);

        assertNotNull(members);
        assertEquals(2, members.size());
        assertEquals("User1", members.get(0).getUsername());
        assertEquals("User2", members.get(1).getUsername());

        verify(groupRepository, times(1)).getMembersByGroupId(groupId);
    }

    @Test
    public void testEditGroup() {
        Group existingGroup = new Group();
        existingGroup.setGroup_id(1L);
        existingGroup.setName("Old Name");
        existingGroup.setAccess("Private");
        existingGroup.setUrlImg("http://example.com/old-image.jpg");

        Group updatedGroup = new Group();
        updatedGroup.setGroup_id(1L);
        updatedGroup.setName("New Name");
        updatedGroup.setAccess("Public");
        updatedGroup.setUrlImg("http://example.com/new-image.jpg");

        when(groupRepository.findById(1L)).thenReturn(Optional.of(existingGroup));
        when(groupRepository.save(any(Group.class))).thenReturn(updatedGroup);

        groupService.editGroup(updatedGroup);

        verify(groupRepository, times(1)).findById(1L);
        verify(groupRepository, times(1)).save(existingGroup);

        assertEquals("New Name", existingGroup.getName());
        assertEquals("Public", existingGroup.getAccess());
        assertEquals("http://example.com/new-image.jpg", existingGroup.getUrlImg());
    }
}
