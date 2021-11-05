package com.revature.models;

import java.util.Objects;

public class Attachment {

    private int id;
    private int requestId;
    private int supplierId;
    private int demanderId;
    private boolean satisfied;
    private String filepath;
    private String addedText;

    public Attachment() {
    }

    public Attachment(int id, int requestId, int supplierId, int demanderId, boolean satisfied, String filepath, String addedText) {
        this.id = id;
        this.requestId = requestId;
        this.supplierId = supplierId;
        this.demanderId = demanderId;
        this.satisfied = satisfied;
        this.filepath = filepath;
        this.addedText = addedText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getDemanderId() {
        return demanderId;
    }

    public void setDemanderId(int demanderId) {
        this.demanderId = demanderId;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getAddedText() {
        return addedText;
    }

    public void setAddedText(String addedText) {
        this.addedText = addedText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return id == that.id && requestId == that.requestId && supplierId == that.supplierId && demanderId == that.demanderId && satisfied == that.satisfied && Objects.equals(filepath, that.filepath) && Objects.equals(addedText, that.addedText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestId, supplierId, demanderId, satisfied, filepath, addedText);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", requestId=" + requestId +
                ", supplierId=" + supplierId +
                ", demanderId=" + demanderId +
                ", satisfied=" + satisfied +
                ", filepath='" + filepath + '\'' +
                ", addedText='" + addedText + '\'' +
                '}';
    }
}
