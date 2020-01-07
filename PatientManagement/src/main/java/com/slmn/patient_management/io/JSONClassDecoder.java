package com.slmn.patient_management.io;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public abstract class JSONClassDecoder {
    public abstract ArrayList run(ArrayList<LinkedTreeMap> objects);
}
