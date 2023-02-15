package com.sisk.appoint.service;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.entity.Role;
import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.repository.AppointUserRepository;
import com.sisk.appoint.repository.RoleRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional()
public class AppointUserServiceImpl implements AppointUserService {

    @Autowired
    AppointUserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Override
    public AppointUser saveUser(AppointUser user) {
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void saveUserRole(String email, RoleType roleType) {
        Optional<AppointUser> user = userRepository.findByEmail(email);
        Optional<Role> role = roleRepository.findByName(roleType);
        if (user.isPresent() && role.isPresent()){
            user.get().addRole(role.get());
        }
    }

    @Override
    public List<AppointUser> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public AppointUser getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new BadCredentialsException("Invalid credentials"));
    }


}
