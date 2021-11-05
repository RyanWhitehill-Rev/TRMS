package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Event;

import com.revature.services.EventServiceImpl;

import com.revature.util.LogUtil;
import io.javalin.http.Handler;

import java.util.List;

public class EventController {

    Gson gson = new Gson();
    EventServiceImpl es;

    public EventController(EventServiceImpl es) {
        this.es = es;
    }


    public Handler getEventById = (ctx) -> {

        String eventIdString = ctx.pathParam("e_id");
        int eventId = -1;

        try {
            eventId = Integer.parseInt(eventIdString);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }

        if(eventId == -1) {
            LogUtil.logger.error("Bad event ID");
            ctx.result("Bad event ID");
            ctx.status(400);
        }

        Event returnedEvent = es.getEventById(eventId);
        if(returnedEvent == null) {
            ctx.result("Something went wrong retrieving an event.");
            ctx.status(500);
        }

        ctx.result(gson.toJson(returnedEvent));
        ctx.status(200);
    };

    //addEvent() -> See Request Controller!
}
