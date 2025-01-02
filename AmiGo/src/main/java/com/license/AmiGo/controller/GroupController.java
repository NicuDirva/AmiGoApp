package com.license.AmiGo.controller;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Group;
import com.license.AmiGo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
public class GroupController {
    @Autowired
    private GroupService groupService;

    @PostMapping("/add")
    public Group add(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }
    @GetMapping("/getAll")
    public List<Group> getAllGroup() {
        return groupService.getAllGroup();
    }
    @PostMapping("/getGroupByCreatorId")
    public List<Group> getGroupByCreatorId(@RequestBody Long creator_id) {
        return groupService.getGroupByCreatorId(creator_id);
    }
    @PostMapping("/getMembersByGroupId")
    public List<Account> getMembersByGroupId(@RequestBody Long group_id) {
        return groupService.getMembersByGroupId(group_id);
    }
    @PatchMapping("/editGroup")
    public void editGroup(@RequestBody Group group) {
        groupService.editGroup(group);
    }
}
