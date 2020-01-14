package com.slmn.patient_management.io;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class JSONObjectFile {
    private String filename;

    public JSONObjectFile(String filename) {
        this.filename = filename;
    }

    public LinkedTreeMap readOrCreateEmpty() {
        Gson gson = new Gson();
        JSONFile file = new JSONFile(this.filename);
        return gson.fromJson(file.readOrCreate("{\"PREVIOUS_ID\": 0.0, \"APPOINTMENT_LENGTH\":30.0,\"SURGERY_OPEN\":8.0,\"SURGERY_CLOSE\":19.0}"), LinkedTreeMap.class);
    }

    public void write(LinkedTreeMap items) {
        Gson gson = new Gson();
        JSONFile file = new JSONFile(this.filename);
        file.write(gson.toJson(items));
    }
}
