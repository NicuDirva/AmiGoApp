package com.license.AmiGo.service;

import com.license.AmiGo.model.Profile;
import java.util.List;

public interface ProfileService {
    void saveProfile(Profile profile);
    List<Profile> getAllProfile();
    void editDescription(Profile profile);
    void editAvatar(Profile profile);
    void editGender(Profile profile);
    void editAccess(Profile profile);
    void deleteProfileByAccountId(long account_id);

}
