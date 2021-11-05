package com.revature.repositories;

import com.revature.models.Event;

public interface EventRepo {

    public Event getEventById(int id);
    public Event addEvent(Event event);

}
