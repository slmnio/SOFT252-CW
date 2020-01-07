/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.slmn.patient_management.core;

import com.slmn.patient_management.io.JSONArrayDecoder;
import com.slmn.patient_management.io.UserDecoder;
import com.slmn.patient_management.user_structures.Administrator;

import java.util.ArrayList;

/**
 * @author Jill
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            JSONArrayDecoder decoder = new JSONArrayDecoder("admins.json", new UserDecoder());

            ArrayList<Administrator> admins;
            admins = decoder.decode();

            System.out.println("Admins loaded: " + admins.size());

            for (Object item : admins) {
                System.out.println(item.getClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
