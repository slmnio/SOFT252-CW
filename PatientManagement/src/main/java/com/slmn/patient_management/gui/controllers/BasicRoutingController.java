package com.slmn.patient_management.gui.controllers;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.gui.views.creators.AccountCreatorView;
import com.slmn.patient_management.gui.views.creators.AccountRequestCreateView;

public class BasicRoutingController extends Controller  {
    public BasicRoutingController() {

    }

    public void routeToAccountCreator() {
        Main.switchView(new AccountCreatorView()); // to account creator form
    }
    public void routeToAccountRequester() { Main.switchView(new AccountRequestCreateView()); }
}
