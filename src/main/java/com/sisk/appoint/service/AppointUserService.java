package com.sisk.appoint.service;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.entity.Role;
import com.sisk.appoint.entity.RoleType;

import java.util.List;

public interface AppointUserService {
    AppointUser saveUser(AppointUser user);
    Role saveRole(Role role);

    void saveUserRole(String email, RoleType roleType);

    List<AppointUser> getUsers();

    AppointUser getUserByEmail(String email);
}
