package com.revature.services;

import com.revature.models.Event;

import java.math.BigDecimal;
import java.math.BigInteger;

public interface EventService {

    public Event getEventById(int id);
    public Event addEvent(int EventCategoryId, BigDecimal eventCost, Long eventTime, String eventLocation, String description, String justification);

}
