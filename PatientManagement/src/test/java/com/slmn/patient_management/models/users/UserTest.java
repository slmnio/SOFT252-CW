package com.slmn.patient_management.models.users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserTest {

    private User admin;

    public UserTest() {
        admin = new Administrator("Solomon", "Cammack", "10 Babbage", "password");
    }

    // most getters and setters don't need to be tested

    @Test
    void getFullName() {
        assertEquals("Solomon Cammack", admin.getFullName(), "User class concatenates name properly");
    }

    @Test
    void getID() {
        // test later when id is pulled from a central store
        assertTrue(admin.getID().startsWith("A"), "Admin ID starts with A");
    }

    @Test
    void passwordMatches() {
        assertTrue(admin.passwordMatches("password"), "Password auth");
    }

    @Test
    void isAdmin() {
        assertTrue(admin.isAdmin(), "Check that self-class booleans work");
    }
}