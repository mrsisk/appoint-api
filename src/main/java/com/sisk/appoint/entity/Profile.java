package com.sisk.appoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    private String surname;

    private String phone;


    private LocalDate dob;


    private ZonedDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonIgnore
    @OneToOne(mappedBy = "profile")
    private AppointUser user;

    public Profile() {
    }

    public Profile(String name, String surname, String phone, LocalDate dob, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.createdAt = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public AppointUser getUser() {
        return user;
    }

    public void setUser(AppointUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
