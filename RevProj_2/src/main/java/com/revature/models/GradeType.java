package com.revature.models;

import java.util.Objects;

public class GradeType {

    private int id;
    private String passType;
    private String passingGrade;
    private boolean presentationRequired;

    public GradeType() {
    }

    public GradeType(int id, String passType, String passingGrade, boolean presentationRequired) {
        this.id = id;
        this.passType = passType;
        this.passingGrade = passingGrade;
        this.presentationRequired = presentationRequired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassType() {
        return passType;
    }

    public void setPassType(String passType) {
        this.passType = passType;
    }

    public String getPassingGrade() {
        return passingGrade;
    }

    public void setPassingGrade(String passingGrade) {
        this.passingGrade = passingGrade;
    }

    public boolean isPresentationRequired() {
        return presentationRequired;
    }

    public void setPresentationRequired(boolean presentationRequired) {
        this.presentationRequired = presentationRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeType gradeType = (GradeType) o;
        return id == gradeType.id && presentationRequired == gradeType.presentationRequired && Objects.equals(passType, gradeType.passType) && Objects.equals(passingGrade, gradeType.passingGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passType, passingGrade, presentationRequired);
    }

    @Override
    public String toString() {
        return "GradeType{" +
                "id=" + id +
                ", passType='" + passType + '\'' +
                ", passingGrade='" + passingGrade + '\'' +
                ", presentationRequired=" + presentationRequired +
                '}';
    }
}


