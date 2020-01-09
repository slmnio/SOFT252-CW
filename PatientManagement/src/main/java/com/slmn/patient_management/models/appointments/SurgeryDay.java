package com.slmn.patient_management.models.appointments;

import com.slmn.patient_management.io.SystemDatabase;
import com.slmn.patient_management.models.users.Doctor;

import java.util.ArrayList;

public class SurgeryDay {
    private ArrayList<TimeSlot> timeSlots;

    private String zeropad(int input) {
        String string = Integer.toString(input);
        if (string.length() == 0) return "00";
        if (string.length() == 1) return "0" + string;
        if (string.length() == 2) return string;
        return string;
    }

    private String getTime(double hour) {
        ///System.out.println("hour " + hour);
        return String.format("%s:%s", zeropad((int) Math.floor(hour)), zeropad((int) Math.floor((hour - Math.floor(hour)) * 60)));
    }

    public void generateDoctorSlots(String date, Doctor doctor) {
        this.generate();
        for (TimeSlot timeslot : timeSlots) {
            if (doctor.hasAppointmentAt(date, timeslot)) {
                timeslot.setOccupied(true);
            }
        }
    }

    public void generate() {
        this.timeSlots = new ArrayList<>();
        SystemDatabase database = SystemDatabase.connect();

        int minutesPerSlot = database.getEnvWithDefault("APPOINTMENT_LENGTH", 60);
        int slotsPerHour = (int) Math.floor(60.0f / minutesPerSlot);
        int startHour = (int) database.getEnvWithDefault("SURGERY_OPEN", 8);
        int hoursPerDay = ((int) database.getEnvWithDefault("SURGERY_CLOSE", 19)) - startHour - 1;
        int totalSlotCount = (hoursPerDay * slotsPerHour);

        System.out.println(String.format("Minutes per slot: %d. Slots per hour: %d. Hours per day: %d. Slots in total: %d", minutesPerSlot, slotsPerHour, hoursPerDay, totalSlotCount));

        for (int i = 0; i < totalSlotCount; i++) {
            String formattedTime = this.getTime(startHour + (i / (double) slotsPerHour));
            timeSlots.add(new TimeSlot(formattedTime));
        }


    }
}
