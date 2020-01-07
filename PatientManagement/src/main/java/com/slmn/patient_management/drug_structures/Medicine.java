package com.slmn.patient_management.drug_structures;

public class Medicine {
    private String name;
    private int stockCount;

    public Medicine(String name) {
        this.name = name;
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
}
