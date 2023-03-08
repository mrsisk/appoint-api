package com.sisk.appoint.service;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.entity.Profile;
import com.sisk.appoint.entity.Role;
import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.model.PageResult;
import com.sisk.appoint.model.ProfileRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AppointUserService {
    AppointUser saveUser(AppointUser user);
    Role saveRole(Role role);

    void saveUserRole(String email, RoleType roleType);

    Page<AppointUser> getUsers(int page, int size);

    AppointUser getUserByEmail(String email);

    Profile saveProfile(ProfileRequest profileRequest, String email);

    List<Profile> profiles();
}
