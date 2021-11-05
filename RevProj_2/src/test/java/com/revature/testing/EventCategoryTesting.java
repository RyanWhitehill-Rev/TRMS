package com.revature.testing;

import com.revature.models.EventCategory;

import com.revature.repositories.EventCategoryRepoHbImpl;
import com.revature.services.EventCategoryServiceImpl;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class EventCategoryTesting {

    EventCategoryRepoHbImpl ecr = new EventCategoryRepoHbImpl();
    EventCategoryServiceImpl ecs = new EventCategoryServiceImpl(ecr);

    @Test
    public void getAllEventCategoriesTest() {
        List<EventCategory> expectedList = new ArrayList<>();
        expectedList.add(new EventCategory(1, "University Course", 80));
        expectedList.add(new EventCategory(2, "Seminar", 60));
        expectedList.add(new EventCategory(3, "Certification Preparation Class", 75));
        expectedList.add(new EventCategory(4, "Certification", 100));
        expectedList.add(new EventCategory(5, "Technical Training", 90));
        expectedList.add(new EventCategory(6, "Other", 30));

        List<EventCategory> returnedList = ecr.getAllEventCategories();
        assertEquals(expectedList, returnedList);
    }

    @Test
    public void getEventCategoryByIdTest() {
        EventCategory expectResult = new EventCategory(1, "University Course", 80);
        EventCategory returnedResult = ecr.getEventCategoryById(1);
        assertEquals(expectResult, returnedResult);
    }

    //Service

    @Test
    public void getAllEventCategoriesTestService() {
        List<EventCategory> expectedList = new ArrayList<>();
        expectedList.add(new EventCategory(1, "University Course", 80));
        expectedList.add(new EventCategory(2, "Seminar", 60));
        expectedList.add(new EventCategory(3, "Certification Preparation Class", 75));
        expectedList.add(new EventCategory(4, "Certification", 100));
        expectedList.add(new EventCategory(5, "Technical Training", 90));
        expectedList.add(new EventCategory(6, "Other", 30));

        List<EventCategory> returnedList = ecs.getAllEventCategories();
        assertEquals(expectedList, returnedList);
    }

    @Test
    public void getEventCategoryByIdTestService() {
        EventCategory expectResult = new EventCategory(1, "University Course", 80);
        EventCategory returnedResult = ecs.getEventCategoryById(1);
        assertEquals(expectResult, returnedResult);
    }


}
