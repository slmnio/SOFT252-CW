package com.slmn.patient_management.gui.controllers;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.user_structures.User;

public class AccountCreatorController extends Controller {

    public User createUser(Class type, LinkedTreeMap details) {

        String code = type.getSimpleName().substring(0,1);

        return User.createAppropriateUser(details);
    }
}
