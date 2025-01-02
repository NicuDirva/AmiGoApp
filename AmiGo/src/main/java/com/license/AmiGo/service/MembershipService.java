package com.license.AmiGo.service;

import com.license.AmiGo.model.Membership;

import java.util.List;

public interface MembershipService {
    void saveMembership(Membership membership);
    List<Membership> getAllMembership();
    List<Membership> getMembershipByAccountId(Long account_id);
    List<Membership> getMembershipByGroupId(Long group_id);
    void deleteMembershipById(Long membership_id);
}
