/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patient_management.user_structures;

import com.google.gson.internal.LinkedTreeMap;

/**
 *
 * @author Jill
 */
public class Doctor extends User {
    public Doctor (String givenName, String surname, String address, String password) {
        super("D", givenName, surname, address, password);
    }

    public Doctor(LinkedTreeMap object) {
        super(object);
    }
}