package com.slmn.patient_management.drug_structures;

import com.slmn.patient_management.user_structures.*;

public class Prescription {
    private Medicine medicine;
    private int quantity;
    private String dosage;
    private Patient patient;

    public Prescription(Patient patient, Medicine medicine, int quantity, String dosage) {
        this(medicine, quantity, dosage);
        this.patient = patient;
    }

    public Prescription(Medicine medicine, int quantity, String dosage) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    public Medicine getMedicine() { return medicine; }
    public int getQuantity() { return quantity; }
    public String getDosage() { return dosage; }

    @Override
    public String toString() {
        return String.format("%d x %s (%s)", this.quantity, this.medicine, this.dosage);
    }
}
