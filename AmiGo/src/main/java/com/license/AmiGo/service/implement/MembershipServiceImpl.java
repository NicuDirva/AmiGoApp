package com.license.AmiGo.service.implement;

import com.license.AmiGo.model.Membership;
import com.license.AmiGo.repository.MembershipRepository;
import com.license.AmiGo.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceImpl implements MembershipService {
    @Autowired
    private MembershipRepository membershipRepository;
    public void saveMembership(Membership membership) {
        membershipRepository.save(membership);
    }
    public List<Membership> getAllMembership() {
        return membershipRepository.findAll();
    }
    public List<Membership> getMembershipByAccountId(Long account_id) {
        return membershipRepository.findByAccountId(account_id);
    }
    public List<Membership> getMembershipByGroupId(Long group_id) {
         return membershipRepository.findByGroupId(group_id);
    }
    public void deleteMembershipById(Long membership_id) {
        membershipRepository.deleteById(membership_id);
    }
}