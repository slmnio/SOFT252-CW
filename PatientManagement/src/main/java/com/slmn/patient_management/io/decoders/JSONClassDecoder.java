package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public abstract class JSONClassDecoder {
    // AL<LinkedTreeMap> -> AL<Type>
    public abstract ArrayList decode(ArrayList<LinkedTreeMap> objects);

    // AL<Type> -> AL<LinkedTreeMap>
    // Most of these will be object casts, but it catches stuff like object.patient -> object.patient_id
    // Can generally use a standard cast, override where it matters
    // public abstract ArrayList encode(ArrayList objects);
    public ArrayList encode(ArrayList objects) {
        return (ArrayList<LinkedTreeMap>) objects;
    }
}
