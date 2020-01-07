package com.slmn.patient_management.core;

import com.slmn.patient_management.io.SystemDatabase;

public class Main {
    public static SystemDatabase database;
    public static void main(String[] args) {
        // Main entry point of the program

        Main.database = new SystemDatabase();

    }
}
