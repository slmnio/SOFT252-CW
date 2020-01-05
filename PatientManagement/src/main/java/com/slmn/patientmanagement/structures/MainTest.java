/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patientmanagement.structures;

import java.util.ArrayList;

/**
 *
 * @author Jill
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        ArrayList<User> list = new ArrayList<User>();
        
        list.add(new Administrator("Solomon", "Cammack", "upstairs"));
        list.add(new Secretary("Solomon", "Cammack", "upstairs"));
        list.add(new Doctor("Solomon", "Cammack", "upstairs"));
        list.add(new Patient("Solomon", "Cammack", "upstairs", 20, "Male"));
        
        
        list.forEach(item -> System.out.println(item.describe()));
        
        
        
    }
    
}
