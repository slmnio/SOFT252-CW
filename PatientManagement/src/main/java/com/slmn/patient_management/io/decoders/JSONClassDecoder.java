package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

public abstract class JSONClassDecoder {
    // AL<LinkedMap> -> AL<Type>
    public abstract ArrayList decode(ArrayList<LinkedTreeMap> objects);

    // AL<Type> -> AL<Object>
    // Most of these will be object casts, but it catches stuff like object.patient -> object.patient_id
    public abstract ArrayList encode(ArrayList objects);
}
