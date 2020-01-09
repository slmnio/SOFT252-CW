package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.models.appointments.Appointment;

import java.util.ArrayList;

public class AppointmentDecoder extends JSONClassDecoder {
    @Override
    public ArrayList decode(ArrayList<LinkedTreeMap> objects) {
        ArrayList<Appointment> output = new ArrayList<>();
        for (LinkedTreeMap object : objects) {
            output.add(new Appointment(object));
        }
        return output;
    }

    @Override
    public ArrayList encode(ArrayList objects) {
        ArrayList<LinkedTreeMap> output = new ArrayList<>();
        for (Object object : objects) {
            Appointment appointment = (Appointment) object;
            LinkedTreeMap map = new LinkedTreeMap();

            map.put("patient_id", appointment.getPatient().getID());
            map.put("doctor_id", appointment.getDoctor().getID());
            map.put("date", appointment.getDate());
            map.put("timeSlot", appointment.getTimeSlot());

            output.add(map);
        }
        return output;
    }
}
