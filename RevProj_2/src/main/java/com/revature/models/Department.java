package com.revature.models;

import java.util.Objects;

public class Department {

    private int id;
    private String name;
    private int deptHeadId;

    public Department() {
    }

    public Department(int id, String name, int deptHeadId) {
        this.id = id;
        this.name = name;
        this.deptHeadId = deptHeadId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeptHeadId() {
        return deptHeadId;
    }

    public void setDeptHeadId(int deptHeadId) {
        this.deptHeadId = deptHeadId;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deptHeadId=" + deptHeadId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && deptHeadId == that.deptHeadId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, deptHeadId);
    }
}
