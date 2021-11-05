package com.revature.testing;

import com.revature.models.Event;
import com.revature.repositories.EventRepoHbImpl;
import com.revature.services.EventServiceImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EventTesting {

    EventRepoHbImpl er = new EventRepoHbImpl();
    EventServiceImpl es = new EventServiceImpl(er);

    //Repo

    @Test
    public void getEventByIdTest() {
        Long eventTime = Long.valueOf(100000000);
        BigDecimal eventCost = BigDecimal.valueOf(500.00);
        Event expectedResult = new Event(1,1, eventCost, eventTime, "Example Location", "A fake event", "I want money back");
        Event returnedResult = er.getEventById(1);
        assertEquals(expectedResult, returnedResult);
    }

    @Test
    @Order(1)
    public void addEventTest() {
        Long eventTime = Long.valueOf(200000);
        BigDecimal eventCost = BigDecimal.valueOf(850);
        Event expectedResult = new Event(2,1,eventCost, eventTime, "Added Location", "A fake event", "I also want money back");
        Event returnedResult = er.addEvent(expectedResult);
        assertEquals(expectedResult, returnedResult);
    }


    //Service

    @Test
    public void getEventByIdTestService() {
        Long eventTime = Long.valueOf(100000000);
        BigDecimal eventCost = BigDecimal.valueOf(500.00);
        Event expectedResult = new Event(1,1,eventCost, eventTime, "Example Location", "A fake event", "I want money back");
        Event returnedResult = es.getEventById(1);
        assertEquals(expectedResult, returnedResult);
    }

    @Test
    @Order(2)
    public void addEventTestService() {
        Long eventTime = Long.valueOf(200000);
        BigDecimal eventCost = BigDecimal.valueOf(850);
        Event expectedResult = new Event(3,1,eventCost, eventTime, "Added Location", "A fake event", "I also want money back");
        Event returnedResult = es.addEvent(1, eventCost, eventTime, "Added Location", "A fake event", "I also want money back");
        assertEquals(expectedResult, returnedResult);
    }


}
