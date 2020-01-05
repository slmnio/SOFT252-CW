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

public class Patient extends User {
    private int age;
    private String sex; 
    // "sex" is the medically correct term for "gender assigned at birth"
    
    public Patient (String givenName, String surname, String address, int age, String sex) {
        super("P", givenName, surname, address);        
        
        this.age = age;
        this.sex = sex;
    }
}