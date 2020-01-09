package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.models.patient_services.DoctorReport;

import java.util.ArrayList;

public class DoctorReportDecoder extends JSONClassDecoder {

    @Override
    public ArrayList decode(ArrayList<LinkedTreeMap> objects) {
        ArrayList<DoctorReport> output = new ArrayList<>();
        for (LinkedTreeMap object : objects) {
            output.add(new DoctorReport(object));
        }
        return output;
    }

    @Override
    public ArrayList encode(ArrayList objects) {
        ArrayList<LinkedTreeMap> output = new ArrayList<>();

        for (Object object : objects) {
            DoctorReport report = (DoctorReport) object;
            LinkedTreeMap map = new LinkedTreeMap();

            map.put("doctor_id", report.getDoctor().getID());
            map.put("user_comment", report.getUserComment());
            map.put("user_rating", report.getUserRating());
            map.put("admin_feedback", report.getAdminFeedback());

            output.add(map);
        }

        return output;
    }
}
