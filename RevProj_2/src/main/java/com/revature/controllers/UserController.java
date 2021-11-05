package com.revature.controllers;

import com.google.gson.Gson;

import com.revature.models.CMDJsonUser;
import com.revature.models.User;
import com.revature.services.UserServiceImpl;
import com.sun.javaws.IconUtil;
import io.javalin.http.Handler;

public class UserController {

    Gson gson = new Gson();
    UserServiceImpl us;

    public UserController(UserServiceImpl us) {
        this.us = us;
    }

    public Handler logInAttempt = (ctx) -> {
        //Get the body of the request (contains the username+password)
        CMDJsonUser cmdJson = gson.fromJson(ctx.body(), CMDJsonUser.class);

        User returnedUser = us.logInAttempt(cmdJson.getUsername(), cmdJson.getPassword());
        if(returnedUser == null) {
            ctx.result("Bad login attempt");
            ctx.status(400);
        }

        if(returnedUser != null) {
            //Stop sending username/password!
            returnedUser.setUsername("");
            returnedUser.setPassword("");
        }

        ctx.result(gson.toJson(returnedUser));
        ctx.status(200);
    };

}
