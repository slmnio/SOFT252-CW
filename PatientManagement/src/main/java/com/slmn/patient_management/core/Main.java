package com.slmn.patient_management.core;

import com.slmn.patient_management.drug_structures.Medicine;
import com.slmn.patient_management.drug_structures.Prescription;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.user_structures.*;

public class Main {
    public static SystemDatabase database;
    public static void main(String[] args) {
        // Main entry point of the program

        Main.database = new SystemDatabase();

        for (User user: Main.database.doctors) {
            System.out.println(user.describe());
        }

        Main.test();
    }
    public static void test() {
        Medicine m1 = new Medicine("Monoolyfeancolfox");
        Prescription prescription = new Prescription(m1, 2, "Twice daily");
        System.out.println("Prescription dispensed:\n" + prescription);
    }
}
