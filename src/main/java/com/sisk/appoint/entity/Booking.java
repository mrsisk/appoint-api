package com.sisk.appoint.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private ZonedDateTime start;

    private ZonedDateTime endTime;

    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String location;

    private long uid;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private AppointUser user;

    public Booking() {
    }

    public AppointUser getUser() {
        return user;
    }

    public void setUser(AppointUser user) {
        this.user = user;
        this.uid = user.getId();
    }

    public Booking(ZonedDateTime start, ZonedDateTime endTime, String description, Category category, String location) {
        this.start = start;
        this.endTime = endTime;
        this.description = description;
        this.category = category;
        this.location = location;

    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ZonedDateTime getStart() {
        return start;
    }

    public void setStart(ZonedDateTime start) {
        this.start = start;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
