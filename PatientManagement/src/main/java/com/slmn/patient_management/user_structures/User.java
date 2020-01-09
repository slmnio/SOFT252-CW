/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patient_management.user_structures;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.io.SystemDatabase;

/**
 *
 * @author solca
 */
public abstract class User {
    private String ID;
    
    private String givenName;
    private String surname; // will be private with get/set
    private String address;
    private String password;
    static final private int IDLength = 4;

    
    // age and gender aren't required for all users, they can be added in subs

    public User() {
        this.ID = "";
        this.givenName = "";
        this.surname = "";
        this.address = "";
        this.password = "";
    }

    public User(Class type, LinkedTreeMap object) {
        this(object);
        this.ID = this.generateID(type.getSimpleName().substring(0,1));
    }


    public User(LinkedTreeMap object) {
        this.ID = (String) object.get("ID");
        this.givenName = (String) object.get("givenName");
        this.surname = (String) object.get("surname");
        this.address = (String) object.get("address");
        this.password = (String) object.get("password");
    }

    
    public User(String code, String givenName, String surname, String address, String password) {
        this.ID = this.generateID(code);

        this.givenName = givenName;
        this.surname = surname;
        this.address = address;
        this.password = password;
    }

    public static User createAppropriateUser(LinkedTreeMap object) {
        if (((String) object.get("ID")).startsWith("A")) return new Administrator(object);
        if (((String) object.get("ID")).startsWith("D")) return new Doctor(object);
        if (((String) object.get("ID")).startsWith("S")) return new Secretary(object);
        if (((String) object.get("ID")).startsWith("P")) return new Patient(object);
        return new Patient(object);
    }

    public static User createAppropriateUser(String code, String givenName, String surname, String address, String password) {
        if (code.equals("A")) return new Administrator(givenName, surname, address, password);
        if (code.equals("D")) return new Doctor(givenName, surname, address, password);
        if (code.equals("S")) return new Secretary(givenName, surname, address, password);
        return null;
    }

    
    private String generateID(String code) {
        // only allow ADPS for code - enum?
        // get general settings json, auto increment

        int prev = (int) SystemDatabase.connect().getEnvWithDefault("PREVIOUS_ID", 1);
        int newID = ++prev;
        SystemDatabase.connect().env.put("PREVIOUS_ID", newID);

        // should be (1 -> 0001) - 3 '0's to add
        int padCount = (User.IDLength - Integer.toString(newID).length());
        
        return String.format("%s%s%s", code, "0".repeat(padCount), newID);
    }
    
    public String describe() {
        return String.format("[%s] %s: %s %s", this.getClass().getSimpleName(), this.ID, this.givenName, this.surname);
    }
    
    public String getGivenName() { return this.givenName; }
    public String getSurname() { return this.surname; }
    public String getFullName() { return String.format("%s %s", this.givenName, this.surname); }
    public String getAddress() { return this.address; }
    public String getID() { return this.ID; }

    public void setID(String ID) { this.ID = ID; }
    public void setGivenName(String givenName) { this.givenName = givenName; }

    public boolean passwordMatches(String attempt) {
        if (attempt == null) attempt = "";
        return this.password.equals(attempt);
    }

    public boolean isAdmin() { return Administrator.class.equals(this.getClass()); }
    public boolean isSecretary() { return Secretary.class.equals(this.getClass()); }
    public boolean isDoctor() { return Doctor.class.equals(this.getClass()); }
    public boolean isPatient() { return Patient.class.equals(this.getClass()); }


    // Tells classes to remove their notifications/appointments etc
    public abstract void destroyDependencies();
}
