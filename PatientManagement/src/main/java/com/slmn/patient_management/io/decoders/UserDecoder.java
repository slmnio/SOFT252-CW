package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.user_structures.*;

import java.util.ArrayList;

public class UserDecoder extends JSONClassDecoder {
    @Override
    public ArrayList<User> decode(ArrayList<LinkedTreeMap> objects) {

        ArrayList<User> output = new ArrayList<>();
        for (LinkedTreeMap object: objects) {
            User user = User.createAppropriateUser(object);
            output.add(user);
        }

        return output;
    }
}
