package com.slmn.patient_management.io;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public abstract class JSONClassDecoder {
    // AL<LinkedMap> -> AL<Type>
    public abstract ArrayList decode(ArrayList<LinkedTreeMap> objects);

    // AL<Type> -> AL<Object>
    public abstract ArrayList encode(ArrayList objects);
}
