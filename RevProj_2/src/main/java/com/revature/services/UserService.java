package com.revature.services;

import com.revature.models.User;

import java.util.List;

public interface UserService {

    public User logInAttempt(String usernameIn, String password);

    public User getUserById(int id);
    //public User getUser(int id);
    public List<User> getUsersBySupervisor(int id);
    public List<User> getUsersByDepartment(int id);



}
