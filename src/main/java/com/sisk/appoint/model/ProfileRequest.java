package com.sisk.appoint.model;

import com.sisk.appoint.entity.Gender;

public record ProfileRequest(String name, String surname, String phone, String dob, Gender gender) {
}
