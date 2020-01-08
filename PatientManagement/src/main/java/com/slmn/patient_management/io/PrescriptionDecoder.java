package com.slmn.patient_management.io;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.drug_structures.Prescription;
import com.slmn.patient_management.user_structures.Patient;
import com.slmn.patient_management.user_structures.User;

import java.util.ArrayList;

public class PrescriptionDecoder extends JSONClassDecoder {
        @Override
        public ArrayList<Prescription> run(ArrayList<LinkedTreeMap> objects) {
            ArrayList<Prescription> output = new ArrayList<>();
            for (LinkedTreeMap object: objects) {
                Prescription prescription = new Prescription(object);
                output.add(prescription);
            }
            return output;
        }
}
