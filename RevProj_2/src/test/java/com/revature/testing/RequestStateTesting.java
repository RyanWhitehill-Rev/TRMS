package com.revature.testing;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.revature.models.RequestState;
import com.revature.repositories.RequestStateRepoHbImpl;
import com.revature.services.RequestStateServiceImpl;


public class RequestStateTesting {

    RequestStateRepoHbImpl rsr = new RequestStateRepoHbImpl();
    RequestStateServiceImpl rss = new RequestStateServiceImpl(rsr);

    @Test
    public void getRequestStateByIdTest() {
        RequestState expectedResult = new RequestState(10, "Request Submitted - Awaiting supervisor approval");
        RequestState actualResult = rss.getRequestStateById(10);
        assertEquals(expectedResult, actualResult);
    }



}
