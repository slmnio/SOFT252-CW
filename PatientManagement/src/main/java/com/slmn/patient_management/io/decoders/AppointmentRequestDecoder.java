package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.models.appointments.AppointmentRequest;

import java.util.ArrayList;

public class AppointmentRequestDecoder extends JSONClassDecoder {

    @Override
    public ArrayList decode(ArrayList<LinkedTreeMap> objects) {
        ArrayList<AppointmentRequest> output = new ArrayList<>();
        for (LinkedTreeMap object : objects) {
            output.add(new AppointmentRequest(object));
        }
        return output;
    }

    @Override
    public ArrayList encode(ArrayList objects) {
        ArrayList<LinkedTreeMap> output = new ArrayList<>();
        for (Object object : objects) {
            AppointmentRequest request = (AppointmentRequest) object;
            LinkedTreeMap map = new LinkedTreeMap();

            LinkedTreeMap apptMap = new LinkedTreeMap();
            apptMap.put("patient_id", request.getAppointment().getPatient().getID());
            apptMap.put("doctor_id", request.getAppointment().getDoctor().getID());
            apptMap.put("date", request.getAppointment().getDate());
            apptMap.put("timeSlot", request.getAppointment().getTimeSlot());

            map.put("appointment", apptMap);
            output.add(map);
        }
        return output;
    }
}
