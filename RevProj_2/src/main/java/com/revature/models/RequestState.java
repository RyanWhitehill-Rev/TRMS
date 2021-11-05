package com.revature.models;

import java.util.Objects;

public class RequestState {

    private int id;
    private String info;

    public RequestState() {
    }

    public RequestState(int id, String info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestState that = (RequestState) o;
        return id == that.id && Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info);
    }

    @Override
    public String toString() {
        return "RequestState{" +
                "id=" + id +
                ", info='" + info + '\'' +
                '}';
    }
}
