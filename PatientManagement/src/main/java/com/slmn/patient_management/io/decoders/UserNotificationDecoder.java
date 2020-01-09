package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.drug_structures.Medicine;
import com.slmn.patient_management.notifications.SpecificUserNotification;

import java.util.ArrayList;

public class UserNotificationDecoder extends JSONClassDecoder {
    @Override
    public ArrayList<SpecificUserNotification> decode(ArrayList<LinkedTreeMap> objects) {
        ArrayList<SpecificUserNotification> output = new ArrayList<>();

        for (LinkedTreeMap object: objects) {
            SpecificUserNotification notification = new SpecificUserNotification(object);
            output.add(notification);
        }
        return output;
    }
}
