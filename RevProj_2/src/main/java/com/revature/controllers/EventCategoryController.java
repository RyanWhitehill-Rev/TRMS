package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.EventCategory;
import com.revature.services.EventCategoryServiceImpl;
import com.revature.util.LogUtil;
import io.javalin.http.Handler;

import java.util.List;

public class EventCategoryController {

    Gson gson = new Gson();
    EventCategoryServiceImpl ecs;

    public EventCategoryController(EventCategoryServiceImpl ecs) {
        this.ecs = ecs;
    }


    public Handler getAllEventCategories = (ctx) -> {

        List<EventCategory> returnedECs = ecs.getAllEventCategories();
        if(returnedECs == null) {
            ctx.result("Something went wrong retrieving event categories - check DB setup!");
            ctx.status(500);
        }

        ctx.result(gson.toJson(returnedECs));
        ctx.status(200);
    };


    public Handler getEventCategoryById = (ctx) -> {

        String requestedEventCategoryIdString = ctx.pathParam("ec_id");
        int requestedEventCategoryId = -1;

        try {
            requestedEventCategoryId = Integer.parseInt(requestedEventCategoryIdString);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }
        if(requestedEventCategoryId == -1) {
            LogUtil.logger.error("Bad event category ID");
            ctx.result("Bad event category ID");
            ctx.status(400);
        }

        EventCategory returnedEC = ecs.getEventCategoryById(requestedEventCategoryId);
        if(returnedEC == null) {
            LogUtil.logger.warn("Could not retrieve event category");
            ctx.result("Could not retrieve event category");
            ctx.status(400);
        }

        ctx.result(gson.toJson(returnedEC));
        ctx.status(200);
    };
}
