package com.slmn.patient_management.controllers;

import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.drugs.Medicine;
import com.slmn.patient_management.models.drugs.Prescription;
import com.slmn.patient_management.models.notifications.NotificationHandler;
import com.slmn.patient_management.models.users.Patient;

import java.util.ArrayList;

public class DrugController extends Controller {
    public DrugController() {

    }

    public void createPrescription(Patient patient, Medicine medicine, String quantityInput, String dosage) {
        int quantity = Integer.parseInt(quantityInput);
        Prescription prescription = new Prescription(patient, medicine, quantity, dosage);
        SystemDatabase.connect().prescriptions.add(prescription);
        SystemDatabase.connect().writeAll();
        this.showInfoMessage(String.format("Prescription of %dx %s created for %s", prescription.getQuantity(), prescription.getMedicine(), prescription.getPatient().getFullName()), "Prescription created");
    }

    public void createMedicine(String name) {
        Medicine medicine = new Medicine(name);
        SystemDatabase.connect().medicines.add(medicine);

        NotificationHandler.notifySecretaries(String.format("A new drug: \"%s\" requires stock to be ordered.", medicine.getName()));
        // handler writes to db
        this.showInfoMessage(String.format("Medicine \"%s\" has been created and sent to a secretary for ordering.", medicine.getName()), "Medicine created");
    }

    public ArrayList<Prescription> getPrescriptions(Patient patient) {
        ArrayList<Prescription> output = new ArrayList<>();
        for (Prescription prescription : SystemDatabase.connect().prescriptions) {
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
        SystemDatabase.connect().writeAll();
        showInfoMessage(String.format("Stock for %s has been increased by %d to %d", medicine.getName(), quantity, medicine.getStockCount()), "Stock increased");
    }

    public void handleDispensePrescription(Prescription prescription) {
        if (!this.canDispensePrescription(prescription)) {
            this.showErrorMessage("There is not enough stock to dispense this prescription");
            return;
        }

        if (confirmMessage(String.format("Confirm dispension of %dx %s", prescription.getQuantity(), prescription.getMedicine().getName()), "Confirm dispension")) {
            this.dispensePrescription(prescription);
            this.showInfoMessage("Prescription dispensed.", "Prescription dispensed");
        }
    }
}
