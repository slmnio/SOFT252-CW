package com.slmn.patient_management.drug_structures;

import com.google.gson.internal.LinkedTreeMap;

public class Medicine {
    private String name;
    private int stockCount;

    public Medicine(String name, int stockCount) {
        this.name = name;
        this.stockCount = stockCount;
    }

    public Medicine(LinkedTreeMap object) {
        this.name = (String) object.get("name");
        this.stockCount = (int) ((double) object.get("stockCount"));
    }

    // Shouldn't need getters/setters for name: no edits, can use constructor/tostring for set/get

    public int getStockCount() { return this.stockCount; }

    public boolean canDispense(int requestCount) { return this.stockCount >= requestCount; }

    public void dispense(int requestCount) {
        if (canDispense(requestCount)) {
            this.stockCount -= requestCount;
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() { return this.toString(); }
}
