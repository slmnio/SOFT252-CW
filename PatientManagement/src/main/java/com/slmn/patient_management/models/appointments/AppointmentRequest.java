package com.slmn.patient_management.models.appointments;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.io.SystemDatabase;

public class AppointmentRequest {
    private Appointment appointment;

    public AppointmentRequest(Appointment appointment) {
        this.appointment = appointment;
    }
    public AppointmentRequest(LinkedTreeMap map) {
        this.appointment = new Appointment((LinkedTreeMap) map.get("appointment"));
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public Appointment approve() {
        SystemDatabase.connect().appointments.add(appointment);
        SystemDatabase.connect().appointmentRequests.remove(this);
        SystemDatabase.connect().writeAll();
        return appointment;
    }
    public void decline() {
        SystemDatabase.connect().appointmentRequests.remove(this);
        SystemDatabase.connect().writeAll();
    }


}
