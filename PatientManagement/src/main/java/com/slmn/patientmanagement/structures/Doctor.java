/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patientmanagement.structures;

/**
 *
 * @author Jill
 */
public class Doctor extends User {
    public Doctor (String givenName, String surname, String address) {
        super("D", givenName, surname, address);        
    }
}