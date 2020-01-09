package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.models.notifications.UserTypeNotification;

import java.util.ArrayList;

public class UserTypeNotificationDecoder extends JSONClassDecoder {
    @Override
    public ArrayList<UserTypeNotification> decode(ArrayList<LinkedTreeMap> objects) {
        ArrayList<UserTypeNotification> output = new ArrayList<>();

        for (LinkedTreeMap object : objects) {
            UserTypeNotification notification = new UserTypeNotification(object);
            output.add(notification);
        }
        return output;
    }
}
