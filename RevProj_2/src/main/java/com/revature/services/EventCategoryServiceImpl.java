package com.revature.services;

import com.revature.models.EventCategory;
import com.revature.repositories.EventCategoryRepoHbImpl;

import java.util.List;

public class EventCategoryServiceImpl implements EventCategoryService{

    EventCategoryRepoHbImpl ecr;

    public EventCategoryServiceImpl(EventCategoryRepoHbImpl ecr) {
        this.ecr = ecr;
    }


    @Override
    public List<EventCategory> getAllEventCategories() {
        return ecr.getAllEventCategories();
    }

    @Override
    public EventCategory getEventCategoryById(int id) {
        return ecr.getEventCategoryById(id);
    }
}
