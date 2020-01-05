/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patient_management.user_structures;

import java.util.ArrayList;

/**
 * @author Jill
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<User> list = new ArrayList<User>();

        list.add(new Administrator("Solomon", "Cammack", "upstairs", "password"));
        list.add(new Secretary("Solomon", "Cammack", "upstairs", "password"));
        list.add(new Doctor("Solomon", "Cammack", "upstairs", "password"));
        list.add(new Patient("Solomon", "Cammack", "upstairs", "password", 20, "Male"));


        list.forEach(item -> System.out.println(item.describe()));

    }
}
