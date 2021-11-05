package com.revature.repositories;

import com.revature.models.EventCategory;

import java.util.List;

public interface EventCategoryRepo {

    public List<EventCategory> getAllEventCategories();
    public EventCategory getEventCategoryById(int id);

}
