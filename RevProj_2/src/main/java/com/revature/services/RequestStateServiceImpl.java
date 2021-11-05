package com.revature.services;

import com.revature.models.RequestState;
import com.revature.repositories.RequestStateRepoHbImpl;

public class RequestStateServiceImpl implements RequestStateService{

    RequestStateRepoHbImpl rsr;

    public RequestStateServiceImpl(RequestStateRepoHbImpl rsr) {
        this.rsr = rsr;
    }

    @Override
    public RequestState getRequestStateById(int id) {
        return rsr.getRequestStateById(id);
    }
}
