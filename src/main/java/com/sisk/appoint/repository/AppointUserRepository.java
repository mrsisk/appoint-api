package com.sisk.appoint.repository;

import com.sisk.appoint.entity.AppointUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AppointUserRepository extends JpaRepository<AppointUser, Long> {

    Optional<AppointUser> findByEmail(String email);
}
