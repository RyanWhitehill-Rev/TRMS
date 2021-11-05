package com.revature.repositories;

import com.revature.models.User;

import java.util.List;

public interface UserRepo {

    //public User addUser(User userIn);
    public User getUser(int id);
    //public List<User> getAllUsers();
    //public User updateUser(User userIn);
    //public User deleteUser(int id);

    public User getUserByUsername(String usernameIn);

    public List<User> getUsersBySupervisor(int id);
    public List<User> getUsersByDepartment(int id);

}
