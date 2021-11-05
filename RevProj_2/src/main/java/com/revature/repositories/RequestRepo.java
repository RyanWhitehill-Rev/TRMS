package com.revature.repositories;

import com.revature.models.Request;

import java.util.List;

public interface RequestRepo {

    public Request addRequest(Request requestIn);
    public Request getRequest(int id);
    public List<Request> getAllRequests();
    public Request updateRequest(Request requestIn);
    public Request deleteRequest(int id);

    public List<Request> getAllRequestsByRequesterId(int requesterId);
    public List<Request> getAttnRequestsRequester(int requesterId);
    public List<Request> getAttnRequestsSupervisor(int requesterId);
    public List<Request> getAttnRequestsDeptHead(int requesterId);
    public List<Request> getAttnRequestsBenCo();
}
