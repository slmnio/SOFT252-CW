package com.slmn.patient_management.controllers;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.views.AdminAccountManagerView;
import com.slmn.patient_management.views.PrescriberView;
import com.slmn.patient_management.views.creators.AccountCreatorView;
import com.slmn.patient_management.views.creators.AccountQueueView;
import com.slmn.patient_management.views.creators.AccountRequestCreateView;

public class BasicRoutingController extends Controller {
    public BasicRoutingController() {

    }

    public void routeToAccountCreator() {
        Main.switchView(new AccountCreatorView()); // to account creator form
    }

    public void routeToAccountRequester() {
        Main.switchView(new AccountRequestCreateView());
    }

    public void routeToAccountQueue() {
        Main.switchView(new AccountQueueView());
    }

    public void routeToAdminAccountManager() { Main.switchView(new AdminAccountManagerView());}

    public void routeToPrescriber() {
        Main.switchView(new PrescriberView());
    }
}
