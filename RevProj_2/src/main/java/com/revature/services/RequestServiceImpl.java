package com.revature.services;

import com.revature.models.Request;
import com.revature.repositories.RequestRepo;
import com.revature.repositories.RequestRepoHbImpl;

import java.util.List;

public class RequestServiceImpl implements RequestService{

    private RequestRepoHbImpl rr;

    public RequestServiceImpl(RequestRepoHbImpl rr) {
        this.rr = rr;
    }

    //A lot goes on here
    //Add a new request to the database
    @Override
    public Request addRequest(Request request) {
        return rr.addRequest(request);
    }

    @Override
    public Request getRequest(int id) {
        return rr.getRequest(id);
    }


    @Override
    public Request alterRequestStatusCode(int id, int newStatusCode) {
        //Get the existing request based on id
        Request requestToUpdate = rr.getRequest(id);

        //Change the status of the request
        requestToUpdate.setRequestState(newStatusCode);

        //Save the changes in the database

        return rr.updateRequest(requestToUpdate);
    }

    //Get all the requests made by a user
    @Override
    public List<Request> getAllRequestsByRequesterId(int id) {
        return rr.getAllRequestsByRequesterId(id);
    }

    //Get all requests made by a user where their attention is required
    @Override
    public List<Request> getAttnRequestsRequester(int requesterId) {
        return rr.getAttnRequestsRequester(requesterId);
    }

    //Get all requests made a user where their supervisor's attention is required
    @Override
    public List<Request> getAttnRequestsSupervisor(int requesterId) {
        return rr.getAttnRequestsSupervisor(requesterId);
    }

    @Override
    public List<Request> getAttnRequestsDeptHead(int requesterId) {
        return rr.getAttnRequestsDeptHead(requesterId);
    }

    @Override
    public List<Request> getAttnRequestsBenCo() {
        return rr.getAttnRequestsBenCo();
    }
}
