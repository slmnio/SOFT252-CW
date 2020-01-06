/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patient_management.user_structures;

/**
 *
 * @author solca
 */
public abstract class User /*implements GSONFillable*/ {
    private String ID;
    
    private String givenName;
    private String surname; // will be private with get/set
    private String address;
    private String password;
    static final private int IDLength = 4;
    
    // age and gender aren't required for all users, they can be added in subs
    
    public User(String code, String givenName, String surname, String address, String password) {
        this.ID = this.generateID(code);
        
        this.givenName = givenName;
        this.surname = surname;
        this.address = address;
        this.password = password;
    }

    public static void createAppropriateUser(Object object) {
        User user;



    }
    
    private String generateID(String code) {
        // only allow ADPS for code - enum?
        // get general settings json, auto increment
        int prev = 85;
        
        int newID = ++prev;
        // should be (1 -> 0001) - 3 '0's to add
        int padCount = (User.IDLength - Integer.toString(newID).length());
        
        return String.format("%s%s%s", code, "0".repeat(padCount), newID);
    }
    
    public String describe() {
        return "User "+ this.ID +": " + this.givenName + " " + this.surname;   
    }
    
    public String getGivenName() { return this.givenName; }
    public String getSurname() { return this.surname; }
    public String getFullName() { return String.format("%s %s", this.givenName, this.surname); }
    public String getAddress() { return this.address; }
    public String getID() { return this.ID; }

    public boolean passwordMatches(String attempt) { return this.password.equals(attempt); }

    
}
