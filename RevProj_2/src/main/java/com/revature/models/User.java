package com.revature.models;

import java.util.Objects;

public class User {

    private int id;
    private String firstname;
    private String lastname;
    private int supervisorId;
    private int departmentId;
    private boolean benCo;
    private String username;
    private String password;

    public User() {
    }

    public User(int id, String firstname, String lastname, int supervisorId, int departmentId, boolean isBenCo, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.supervisorId = supervisorId;
        this.departmentId = departmentId;
        this.benCo = isBenCo;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public boolean isBenCo() {
        return benCo;
    }

    public void setBenCo(boolean benCo) {
        this.benCo = benCo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && supervisorId == user.supervisorId && departmentId == user.departmentId && benCo == user.benCo && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, supervisorId, departmentId, benCo, username, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", supervisorId=" + supervisorId +
                ", departmentId=" + departmentId +
                ", benCo=" + benCo +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
