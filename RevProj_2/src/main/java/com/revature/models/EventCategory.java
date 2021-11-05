package com.revature.models;

import java.util.Objects;

public class EventCategory {

    private int id;
    private String category;
    private int reimbursementValue;

    public EventCategory() {
    }

    public EventCategory(int id, String category, int reimbursementValue) {
        this.id = id;
        this.category = category;
        this.reimbursementValue = reimbursementValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReimbursementValue() {
        return reimbursementValue;
    }

    public void setReimbursementValue(int reimbursementValue) {
        this.reimbursementValue = reimbursementValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventCategory that = (EventCategory) o;
        return id == that.id && reimbursementValue == that.reimbursementValue && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, reimbursementValue);
    }
}
