package com.license.AmiGo.service;

import com.license.AmiGo.model.Account;
import com.license.AmiGo.model.Group;

import java.util.List;
import java.util.Map;

public interface GroupService {
    Group saveGroup(Group group);
    List<Group> getAllGroup();
    List<Group> getGroupByCreatorId(long creator_id);
    List<Account> getMembersByGroupId(long group_id);
    void editGroup(Group group);
}
