package com.slmn.patient_management.io;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class JSONArrayFile {
    private String filename;

    public JSONArrayFile(String filename) {
        this.filename = filename;
    }

    public <T> ArrayList<T> readOrCreateEmpty() {
        Gson gson = new Gson();
        JSONFile file = new JSONFile(this.filename);
        return gson.fromJson(file.readOrCreate("[]"), ArrayList.class);
    }


    private <T> ArrayList<T> read() throws FileNotFoundException {
        Gson gson = new Gson();
        JSONFile file = new JSONFile(this.filename);
        return gson.fromJson(file.read(), ArrayList.class);
    }

    public void write(ArrayList list) {
        Gson gson = new Gson();
        JSONFile file = new JSONFile(this.filename);
        file.write(gson.toJson(list));
    }
}
