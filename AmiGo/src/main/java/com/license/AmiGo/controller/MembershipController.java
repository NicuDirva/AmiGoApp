package com.license.AmiGo.controller;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Membership;
import com.license.AmiGo.service.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership")
public class MembershipController {
    @Autowired
    private MembershipService membershipService;

    @PostMapping("/add")
    public void add(@RequestBody Membership membership) {
        membershipService.saveMembership(membership);
    }
    @GetMapping("/getAll")
    public List<Membership> getAllMembership() {
        return membershipService.getAllMembership();
    }
    @GetMapping("/getMembershipByAccountId")
    public List<Membership> getMembershipByAccountId(@RequestBody Long account_id) {
        return membershipService.getMembershipByAccountId(account_id);
    }
    @GetMapping("/getMembershipByGroupId")
    public List<Membership> getMembershipByGroupId(@RequestBody Long group_id) {
        return membershipService.getMembershipByGroupId(group_id);
    }
    @PatchMapping("/deleteMembershipById")
    public void deleteMembershipById(@RequestBody Long membership_id) {
        membershipService.deleteMembershipById(membership_id);
    }
}
