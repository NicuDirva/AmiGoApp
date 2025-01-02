package com.license.AmiGo.service.implement;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Group;
import com.license.AmiGo.repository.GroupRepository;
import com.license.AmiGo.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }
    @Override
    public List<Group> getAllGroup() {
        return groupRepository.findAll();
    }
    public List<Group> getGroupByCreatorId(long creator_id) {
        return groupRepository.getGroupByCreatorId(creator_id);
    }
    public List<Account> getMembersByGroupId(long group_id) {
        return groupRepository.getMembersByGroupId(group_id);
    }
    public void editGroup(Group group) {
        Group existingGroup = groupRepository.findById(group.getGroup_id())
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        existingGroup.setName(group.getName());
        existingGroup.setAccess(group.getAccess());
        existingGroup.setUrlImg(group.getUrlImg());

        groupRepository.save(existingGroup);
    }
}
