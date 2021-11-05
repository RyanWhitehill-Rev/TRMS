package com.revature.controllers;

import com.google.gson.Gson;

import com.revature.models.RequestState;

import com.revature.services.RequestStateServiceImpl;
import com.revature.util.LogUtil;
import io.javalin.http.Handler;


public class RequestStateController {


    Gson gson = new Gson();
    RequestStateServiceImpl rss;

    public RequestStateController(RequestStateServiceImpl rss) {
        this.rss = rss;
    }

    public Handler getRequestStateById = (ctx) -> {

        String requestedRequestStateIdString = ctx.pathParam("rs_id");
        int requestedRequestStateId = -1;

        try {
            requestedRequestStateId = Integer.parseInt(requestedRequestStateIdString);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }
        if(requestedRequestStateId == -1) {
            LogUtil.logger.error("Bad request state ID");
            ctx.result("Bad request state ID");
            ctx.status(400);
        }

        RequestState returnedRS = rss.getRequestStateById(requestedRequestStateId);
        if(returnedRS == null) {
            LogUtil.logger.warn("Could not retrieve request state");
            ctx.result("Could not retrieve request state");
            ctx.status(400);
        }

        ctx.result(gson.toJson(returnedRS));
        ctx.status(200);
    };
}
