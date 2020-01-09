package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.models.drugs.Medicine;

import java.util.ArrayList;

public class MedicineDecoder extends JSONClassDecoder {
        @Override
        public ArrayList<Medicine> decode(ArrayList<LinkedTreeMap> objects) {
            ArrayList<Medicine> output = new ArrayList<>();

            for (LinkedTreeMap object: objects) {
                Medicine medicine = new Medicine(object);
                output.add(medicine);
            }
            return output;
        }
}
