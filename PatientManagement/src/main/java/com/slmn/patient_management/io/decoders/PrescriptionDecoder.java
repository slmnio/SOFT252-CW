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

    @Override
    public ArrayList encode(ArrayList objects) {

        ArrayList<LinkedTreeMap> output = new ArrayList<>();

        for (Object object: objects) {
            Prescription p = (Prescription) object;
            LinkedTreeMap map = new LinkedTreeMap();

            map.put("medicine_name", p.getMedicine().getName());
            map.put("quantity", p.getQuantity());
            map.put("dosage", p.getDosage());
            map.put("patient_id", p.getPatient().getID());

            output.add(map);
        }

        return output;
    }
}
