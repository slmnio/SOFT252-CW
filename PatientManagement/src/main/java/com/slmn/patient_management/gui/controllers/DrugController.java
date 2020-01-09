package com.slmn.patient_management.gui.controllers;

import com.slmn.patient_management.drug_structures.Medicine;
import com.slmn.patient_management.drug_structures.Prescription;
import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.notifications.NotificationHandler;
import com.slmn.patient_management.user_structures.Patient;

import java.util.ArrayList;

public class DrugController extends Controller {
    public DrugController() {

    }

    public void createPrescription(Patient patient, Medicine medicine, String quantityInput, String dosage) {
        int quantity = Integer.parseInt(quantityInput);
        Prescription prescription = new Prescription(patient, medicine, quantity, dosage);
        SystemDatabase.connect().prescriptions.add(prescription);
        SystemDatabase.connect().writeAll();
    }
    public void createMedicine(String name) {
        Medicine medicine = new Medicine(name);
        SystemDatabase.connect().medicines.add(medicine);

        NotificationHandler.notifySecretaries(String.format("A new drug: \"%s\" requires stock to be ordered.", medicine.getName()));
        // handler writes to db
    }
    public ArrayList<Prescription> getPrescriptions(Patient patient) {
        ArrayList<Prescription> output = new ArrayList<>();
        for (Prescription prescription: SystemDatabase.connect().prescriptions) {
            if (prescription.getPatient().equals(patient)) {
                output.add(prescription);
            }
        }
        return output;
    }

    public boolean canDispensePrescription(Prescription prescription) {
        return prescription.canDispense();
    }
    public void dispensePrescription(Prescription prescription) {
        if (!canDispensePrescription(prescription)) return;
        prescription.dispense();
        SystemDatabase.connect().prescriptions.remove(prescription);
        SystemDatabase.connect().writeAll();
    }

    public void stockMedicine(Medicine medicine, String inputQuantity) {
        int quantity = Integer.parseInt(inputQuantity);
        medicine.increaseStock(quantity);
    }
}
