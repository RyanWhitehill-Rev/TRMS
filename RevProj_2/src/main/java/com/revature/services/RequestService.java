package com.revature.services;

import com.revature.models.Request;

import java.util.List;

public interface RequestService {

    public Request addRequest(Request request);

    public Request getRequest(int id);

    public Request alterRequestStatusCode(int id, int newStatusCode);

    public List<Request> getAllRequestsByRequesterId(int id);
    public List<Request> getAttnRequestsRequester(int requesterId);
    public List<Request> getAttnRequestsSupervisor(int requesterId);
    public List<Request> getAttnRequestsDeptHead(int requesterId);

    public List<Request> getAttnRequestsBenCo();

}
