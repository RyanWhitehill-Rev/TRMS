package com.revature.services;

import com.revature.models.EventCategory;

import java.util.List;

public interface EventCategoryService {

    public List<EventCategory> getAllEventCategories();
    public EventCategory getEventCategoryById(int id);

}
