package com.slmn.patient_management.user_structures;

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
        assertEquals(admin.getFullName(), "Solomon Cammack");
    }

    @Test
    void getID() {
        // test later when id is pulled from a central store
    }

    @Test
    void passwordMatches() {
        assertTrue(admin.passwordMatches("password"));
    }
}