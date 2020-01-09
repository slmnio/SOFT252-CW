package com.slmn.patient_management.io.decoders;

import com.google.gson.internal.LinkedTreeMap;
import com.slmn.patient_management.models.users.Patient;
import com.slmn.patient_management.models.users.requests.AccountCreationRequest;
import com.slmn.patient_management.models.users.requests.AccountDeletionRequest;
import com.slmn.patient_management.models.users.requests.AccountRequest;

import java.util.ArrayList;

public class AccountRequestDecoder extends JSONClassDecoder {

    @Override
    public ArrayList decode(ArrayList<LinkedTreeMap> objects) {
        ArrayList<AccountRequest> output = new ArrayList<>();

        for (Object object: objects) {
            LinkedTreeMap map = (LinkedTreeMap) object;

            Patient user = new Patient((LinkedTreeMap) map.get("patient"));

            if (map.get("request_type") == "AccountDeletionRequest") {
                output.add(new AccountDeletionRequest(user));
            } else {
                output.add(new AccountCreationRequest(user));
            }
        }

        return output;
    }

    @Override
    public ArrayList encode(ArrayList requests) {
        ArrayList<LinkedTreeMap> output = new ArrayList<>();

        for (Object object: requests) {
            AccountRequest request = (AccountRequest) object;
            LinkedTreeMap map = new LinkedTreeMap();

            map.put("patient", request.getPatient());
            map.put("request_type", request.getClass().getSimpleName());

            output.add(map);
        }

        return output;
    }
}
