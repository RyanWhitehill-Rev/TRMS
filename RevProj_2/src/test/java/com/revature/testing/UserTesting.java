package com.revature.testing;

import com.revature.models.User;
import com.revature.repositories.UserRepoHbImpl;
import com.revature.services.UserServiceImpl;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTesting {

    UserRepoHbImpl ur = new UserRepoHbImpl();
    UserServiceImpl us = new UserServiceImpl(ur);

    @Test
    public void logInAttemptTest(){
        User expectedUser = new User(1, "Department", "Head A", 2, 1, false, "5", "1");
        User returnedUser = us.logInAttempt("5", "1");
        assertEquals(expectedUser, returnedUser);
    }

    @Test
    public void getUserByIdTest(){
        User expectedUser = new User(1, "Department", "Head A", 2, 1, false, "5", "1");
        User returnedUser = us.getUserById(1);
        assertEquals(expectedUser, returnedUser);
    }

    @Test
    public void getUsersBySupervisorTest(){
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(1, "Department", "Head A", 2, 1, false, "5", "1"));
        List<User> returnedUsers = us.getUsersBySupervisor(2);
        assertEquals(expectedUsers, returnedUsers);
    }

    @Test
    public void getUsersByDepartmentTest(){
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User(2, "Department", "Head B", 1, 2, true, "4", "1"));
        List<User> returnedUsers = us.getUsersByDepartment(2);
        assertEquals(expectedUsers, returnedUsers);
    }

    /*
    //Service
    public User logInAttempt(String usernameIn, String password);
    public User getUserById(int id);
    public List<User> getUsersBySupervisor(int id);
    public List<User> getUsersByDepartment(int id);

    //Repo
    public User getUser(int id);
    public User getUserByUsername(String usernameIn);
    */



}
