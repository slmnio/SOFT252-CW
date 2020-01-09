package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.models.patient_services.PatientRecord;

import java.util.ArrayList;

public class PatientRecordDecoder extends JSONClassDecoder {
    @Override
    public ArrayList decode(ArrayList<LinkedTreeMap> objects) {
        ArrayList<PatientRecord> output = new ArrayList<>();
        for (LinkedTreeMap object : objects) {
            output.add(new PatientRecord(object));
        }
        return output;
    }

    @Override
    public ArrayList encode(ArrayList objects) {
        ArrayList<LinkedTreeMap> output = new ArrayList<>();
        for (Object object : objects) {
            PatientRecord patientRecord = (PatientRecord) object;
            LinkedTreeMap map = new LinkedTreeMap();

            map.put("patient_id", patientRecord.getPatient().getID());
            map.put("content", patientRecord.getContent());

            output.add(map);
        }
        return output;
    }
}
