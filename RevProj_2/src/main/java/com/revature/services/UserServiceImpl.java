package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserRepo;
import com.revature.repositories.UserRepoHbImpl;

import java.util.List;

public class UserServiceImpl implements UserService{

    UserRepoHbImpl ur;

    public UserServiceImpl(UserRepoHbImpl ur) {
        this.ur = ur;
    }

    @Override
    public User logInAttempt(String usernameIn, String password) {

        User returnedUser = ur.getUserByUsername(usernameIn);
        if(returnedUser == null) {
            //No user with that username was found
            return null;
        }

        if(!password.equals(returnedUser.getPassword())) {
            //password does not match
            return null;
        }

        //user found, password matches. Return the user data
        return returnedUser;
    }

    @Override
    public User getUserById(int id) {
        return ur.getUser(id);
    }

    @Override
    public List<User> getUsersBySupervisor(int id) {
        return ur.getUsersBySupervisor(id);
    }

    @Override
    public List<User> getUsersByDepartment(int id) {
        return ur.getUsersByDepartment(id);
    }

}
