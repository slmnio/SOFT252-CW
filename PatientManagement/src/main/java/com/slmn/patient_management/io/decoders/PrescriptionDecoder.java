package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.drug_structures.Prescription;

import java.util.ArrayList;

public class PrescriptionDecoder extends JSONClassDecoder {
        @Override
        public ArrayList<Prescription> decode(ArrayList<LinkedTreeMap> objects) {
            ArrayList<Prescription> output = new ArrayList<>();
            for (LinkedTreeMap object: objects) {
                Prescription prescription = new Prescription(object);
                output.add(prescription);
            }
            return output;
        }
}
