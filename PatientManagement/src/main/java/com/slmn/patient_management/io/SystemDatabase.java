package com.slmn.patient_management.io;

import com.slmn.patient_management.user_structures.*;
import com.slmn.patient_management.drug_structures.*;
import com.slmn.patient_management.io.*;

import java.util.ArrayList;

public class SystemDatabase {
    public ArrayList<Administrator> admins;

    public SystemDatabase() {
        this.admins = this.loadAdmins();
    }

    private ArrayList<Administrator> loadAdmins() {
        JSONArrayDecoder decoder = new JSONArrayDecoder("admins.json", new UserDecoder());
        return decoder.decode();
    }
}
