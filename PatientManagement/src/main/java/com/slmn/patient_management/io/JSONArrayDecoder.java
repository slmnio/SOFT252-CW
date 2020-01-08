package com.slmn.patient_management.io;

import java.util.ArrayList;

public class JSONArrayDecoder {
    private String filename;
    private JSONClassDecoder decoder;

    public JSONArrayDecoder(String filename, JSONClassDecoder decoder) {
        this.filename = filename;
        this.decoder = decoder;
    }

    public ArrayList decode() {
        JSONArrayFile file = new JSONArrayFile(this.filename);
        ArrayList objects = file.readOrCreateEmpty();
        return decoder.decode(objects);
    }

    public void encode(ArrayList objects) {
        JSONArrayFile file = new JSONArrayFile(this.filename);
        file.write(decoder.encode(objects));
    }
}
