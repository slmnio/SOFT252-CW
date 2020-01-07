package com.slmn.patient_management.core;

import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.user_structures.*;

public class Main {
    public static SystemDatabase database;
    public static void main(String[] args) {
        // Main entry point of the program

        Main.database = new SystemDatabase();

        for (Administrator admin: Main.database.admins) {
            System.out.println(admin.describe());
        }

    }
}
