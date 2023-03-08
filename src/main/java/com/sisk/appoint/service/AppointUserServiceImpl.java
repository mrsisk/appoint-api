package com.sisk.appoint.service;

import com.sisk.appoint.entity.AppointUser;
import com.sisk.appoint.entity.Profile;
import com.sisk.appoint.entity.Role;
import com.sisk.appoint.entity.RoleType;
import com.sisk.appoint.model.PageResult;
import com.sisk.appoint.model.ProfileRequest;
import com.sisk.appoint.repository.AppointUserRepository;
import com.sisk.appoint.repository.ProfileRepository;
import com.sisk.appoint.repository.RoleRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional()
public class AppointUserServiceImpl implements AppointUserService {

    @Autowired
    AppointUserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;
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
    public Page<AppointUser> getUsers(int page, int size) {
        Page<AppointUser> results = userRepository.findAll(PageRequest.of(page, size));
        PageResult<AppointUser> res = new PageResult<>(results.getContent(), results.getTotalElements());
        return results;
        //return userRepository.findAll();
    }

    @Override
    public AppointUser getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new BadCredentialsException("Invalid credentials"));
    }

    @Override
    public Profile saveProfile(ProfileRequest profileRequest, String email) {


        AppointUser user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("user not found"));

        try {
            LocalDate date = LocalDate.parse(profileRequest.dob());
            Profile profile = new Profile(profileRequest.name(), profileRequest.surname(), profileRequest.phone(), date, profileRequest.gender());
           // profile.setUser(user);
            user.setProfile(profile);
            return profile;
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public List<Profile> profiles() {
        return profileRepository.findAll();
    }
}
