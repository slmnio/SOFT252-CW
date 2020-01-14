package com.slmn.patient_management.controllers;

import com.slmn.patient_management.core.Main;
import com.slmn.patient_management.models.users.Doctor;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.views.*;
import com.slmn.patient_management.views.appointments.PatientAppointmentRequesterView;
import com.slmn.patient_management.views.creators.AccountCreatorView;
import com.slmn.patient_management.views.creators.AccountQueueView;
import com.slmn.patient_management.views.creators.AccountRequestCreateView;
import com.slmn.patient_management.views.creators.AppointmentQueueView;
import com.slmn.patient_management.views.main_menu.AdminMainMenu;
import com.slmn.patient_management.views.main_menu.DoctorMainMenu;
import com.slmn.patient_management.views.main_menu.PatientMainMenu;
import com.slmn.patient_management.views.structures.ViewWithFrame;

import javax.swing.text.View;

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

    public void routeToAdminAccountManager() {
        Main.switchView(new AdminAccountManagerView());
    }

    public void routeToPrescriber() {
        Main.switchView(new PrescriberView());
    }

    public void routeToPrescriptionViewer(Patient user, ViewWithFrame previous) {
        Main.switchView(new PrescriptionListView(user, previous));
    }

    public void routeToPrescriptionDispenser() {
        Main.switchView(new PrescriptionDispenserView());
    }

    public void routeToMedicineStock() {
        Main.switchView(new MedicineStockView());
    }

    public void routeToRecordsSelfView() {
        Main.switchView(new PatientRecordsSelfView());
    }

    public void routeToRecordsDoctorView(Patient patient) {
        Main.switchView(new PatientRecordsDoctorView(patient));
    }

    public void routeToSecretaryAccountManager() {
        Main.switchView(new PatientManagerView());
    }

    public void routeToDoctorRatingPatient() {
        Main.switchView(new DoctorRatingPatientView());
    }

    public void routeToMedicineCreator() {
        Main.switchView(new MedicineCreatorView());
    }

    public void routeToDoctorRatingAdmin() {
        Main.switchView(new DoctorRatingAdminView(true, new AdminMainMenu()));
    }

    public void routeToDoctorRatingDoctor() {
        Main.switchView(new DoctorRatingAdminView(false, new DoctorMainMenu()));
    }

    public void routeToPatientAppointmentRequester() {
        Main.switchView(new PatientAppointmentRequesterView());
    }

    public void routeToAppointmentQueue() {
        Main.switchView(new AppointmentQueueView());
    }

    public void routeToAppointmentView(Doctor doctor) {
        Main.switchView(new UserAppointmentView(new DoctorMainMenu(), doctor));
    }
    public void routeToAppointmentView(Patient patient) {
        Main.switchView(new UserAppointmentView(new PatientMainMenu(), patient));
    }
}
