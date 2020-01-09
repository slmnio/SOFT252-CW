package com.slmn.patient_management.controllers;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.models.users.User;
import com.slmn.patient_management.views.*;
import com.slmn.patient_management.views.creators.AccountCreatorView;
import com.slmn.patient_management.views.creators.AccountQueueView;
import com.slmn.patient_management.views.creators.AccountRequestCreateView;
import com.slmn.patient_management.views.structures.ViewWithFrame;

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

    public void routeToPrescriptionViewer(Patient user, ViewWithFrame previous) {
        Main.switchView(new PrescriptionListView(user, previous));
    }

    public void routeToPrescriptionDispenser() {
        Main.switchView(new PrescriptionDispenserView());
    }

    public void routeToMedicineStock() { Main.switchView(new MedicineStockView());    }

    public void routeToRecordsSelfView() {
        Main.switchView(new PatientRecordsSelfView());
    }

    public void routeToRecordsDoctorView(Patient patient) {
        Main.switchView(new PatientRecordsDoctorView(patient));
    }

    public void routeToSecretaryAccountManager() {
        Main.switchView(new PatientManagerView());
    }
}
