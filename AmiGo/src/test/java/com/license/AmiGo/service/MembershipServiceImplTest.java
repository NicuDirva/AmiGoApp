package com.license.AmiGo.service;

import com.license.AmiGo.model.Membership;
import com.license.AmiGo.repository.MembershipRepository;
import com.license.AmiGo.service.implement.MembershipServiceImpl;
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

public class MembershipServiceImplTest {

    @Mock
    private MembershipRepository membershipRepository;

    @InjectMocks
    private MembershipServiceImpl membershipService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveMembership() {
        Membership membership = new Membership();
        membership.setMembership_id(1L);
        membership.setAccount_id(101L);
        membership.setGroup_id(201L);

        when(membershipRepository.save(membership)).thenReturn(membership);

        membershipService.saveMembership(membership);

        verify(membershipRepository, times(1)).save(membership);
    }

    @Test
    public void testGetAllMembership() {
        Membership membership1 = new Membership();
        membership1.setMembership_id(1L);
        membership1.setAccount_id(101L);
        membership1.setGroup_id(201L);

        Membership membership2 = new Membership();
        membership2.setMembership_id(2L);
        membership2.setAccount_id(102L);
        membership2.setGroup_id(202L);

        List<Membership> mockMemberships = new ArrayList<>();
        mockMemberships.add(membership1);
        mockMemberships.add(membership2);

        when(membershipRepository.findAll()).thenReturn(mockMemberships);

        List<Membership> memberships = membershipService.getAllMembership();

        assertNotNull(memberships);
        assertEquals(2, memberships.size());
        assertEquals(101L, memberships.get(0).getAccount_id());
        assertEquals(202L, memberships.get(1).getGroup_id());

        verify(membershipRepository, times(1)).findAll();
    }

    @Test
    public void testGetMembershipByAccountId() {
        long accountId = 101L;

        Membership membership = new Membership();
        membership.setMembership_id(1L);
        membership.setAccount_id(accountId);
        membership.setGroup_id(201L);

        List<Membership> mockMemberships = new ArrayList<>();
        mockMemberships.add(membership);

        when(membershipRepository.findByAccountId(accountId)).thenReturn(mockMemberships);

        List<Membership> memberships = membershipService.getMembershipByAccountId(accountId);

        assertNotNull(memberships);
        assertEquals(1, memberships.size());
        assertEquals(accountId, memberships.get(0).getAccount_id());

        verify(membershipRepository, times(1)).findByAccountId(accountId);
    }

    @Test
    public void testGetMembershipByGroupId() {
        long groupId = 201L;

        Membership membership = new Membership();
        membership.setMembership_id(1L);
        membership.setAccount_id(101L);
        membership.setGroup_id(groupId);

        List<Membership> mockMemberships = new ArrayList<>();
        mockMemberships.add(membership);

        when(membershipRepository.findByGroupId(groupId)).thenReturn(mockMemberships);

        List<Membership> memberships = membershipService.getMembershipByGroupId(groupId);

        assertNotNull(memberships);
        assertEquals(1, memberships.size());
        assertEquals(groupId, memberships.get(0).getGroup_id());

        verify(membershipRepository, times(1)).findByGroupId(groupId);
    }

    @Test
    public void testDeleteMembershipById() {
        long membershipId = 1L;

        doNothing().when(membershipRepository).deleteById(membershipId);

        membershipService.deleteMembershipById(membershipId);

        verify(membershipRepository, times(1)).deleteById(membershipId);
    }
}
