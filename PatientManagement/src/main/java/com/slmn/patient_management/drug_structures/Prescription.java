package com.slmn.patient_management.drug_structures;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.user_structures.Patient;

public class Prescription {
    private Medicine medicine;
    private String medicine_name;
    private int quantity;
    private String dosage;
    private Patient patient;
    private String patient_id;

    public Prescription(Patient patient, Medicine medicine, int quantity, String dosage) {
        this(medicine, quantity, dosage);
        this.patient = patient;
    }

    public Prescription(LinkedTreeMap object) {
        this.quantity = (int) ((double)object.get("quantity"));
        this.dosage = (String) object.get("dosage");
        this.medicine_name = (String) object.get("medicine_name");
        this.patient_id = (String) object.get("patient_id");
    }

    public Prescription(Medicine medicine, int quantity, String dosage) {
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    public int getQuantity() { return quantity; }
    public String getDosage() { return dosage; }
    
    public Patient getPatient() {
        if (this.patient == null && this.patient_id != null) {
            this.setPatient((Patient) SystemDatabase.connect().getUser(this.patient_id));
        }
        return this.patient; 
    }


    public boolean canDispense() { return this.getMedicine().getStockCount() >= this.quantity; }

    public void dispense() {
        if (canDispense()) {
            this.getMedicine().dispense(this.quantity);
        }
    }


    public void setPatient(Patient patient) {
        if (this.patient != null) return;
        this.patient = patient;
    }
    
    public String getPatientID() {
        if (this.patient_id == null && this.patient != null) {
            return this.patient.getID();
        }
        return this.patient_id;
    }
    public void setPatientID(String patient_id) {
        this.patient_id = patient_id;
    }


    public Medicine getMedicine() {
        if (this.medicine == null && this.medicine_name != null) {
            this.setMedicine(SystemDatabase.connect().getMedicine(this.medicine_name));
        }
        return this.medicine;
    }

    public void setMedicine(Medicine medicine) {
        if (this.medicine != null) return;
        this.medicine = medicine;
    }

    public String getMedicineName() {
        if (this.medicine_name == null && this.medicine != null) {
            return this.medicine.getName();
        }
        return this.medicine_name;
    }

    public void setMedicineName(String medicine_name) {
        this.medicine_name = medicine_name;
    }
    
    @Override
    public String toString() {
        return String.format("%d x %s (%s)", this.quantity, this.medicine, this.dosage);
    }
}
