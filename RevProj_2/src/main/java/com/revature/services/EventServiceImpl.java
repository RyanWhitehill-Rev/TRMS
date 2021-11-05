package com.revature.services;

import com.revature.models.Event;
import com.revature.repositories.EventRepoHbImpl;

import java.math.BigDecimal;
import java.math.BigInteger;

public class EventServiceImpl implements EventService{

    EventRepoHbImpl er;

    public EventServiceImpl(EventRepoHbImpl er) {
        this.er = er;
    }

    @Override
    public Event getEventById(int id) {
        return er.getEventById(id);
    }

    @Override
    public Event addEvent(int EventCategoryId, BigDecimal eventCost, Long eventTime, String eventLocation, String description, String justification) {
        System.out.println("Adding event");

        Event newEvent = new Event();

        newEvent.setEventTypeId(EventCategoryId);
        newEvent.setEventCost(eventCost);
        newEvent.setEventTime(eventTime);
        newEvent.setEventLocation(eventLocation);
        newEvent.setDescription(description);
        newEvent.setJustification(justification);

        System.out.println(newEvent);

        return er.addEvent(newEvent);
    }
}
