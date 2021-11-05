package com.revature.controllers;

import com.google.gson.Gson;

import com.revature.models.*;
import com.revature.repositories.DepartmentRepoHbImpl;
import com.revature.services.*;

import com.revature.util.LogUtil;
import com.sun.javaws.IconUtil;
import io.javalin.http.Handler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RequestController {

    Gson gson = new Gson();
    RequestServiceImpl rs;
    UserServiceImpl us;
    DepartmentServiceImpl ds;
    EventServiceImpl es;
    AttachmentServiceImpl as;

    public RequestController(RequestServiceImpl rs, UserServiceImpl us, DepartmentServiceImpl ds, EventServiceImpl es, AttachmentServiceImpl as) {
        this.rs = rs;
        this.us = us;
        this.ds = ds;
        this.es = es;
        this.as = as;
    }

    public Handler addRequest = (ctx) -> {

        Request newRequest = new Request();
        CMDJson cmdJson = gson.fromJson(ctx.body(), CMDJson.class);

        //Make a new event with the event data
        Event newEvent = es.addEvent(cmdJson.getEventType(), cmdJson.getEventCost(), cmdJson.getEventTime(), cmdJson.getEventLoca(), cmdJson.getEventDesc(), cmdJson.getEventJust());


        newRequest.setRequesterId(cmdJson.getId());
        newRequest.setRequestState(10);
        newRequest.setRequestTime(System.currentTimeMillis());

        System.out.println(cmdJson.getRmbValue());

        newRequest.setRmbValue(cmdJson.getRmbValue());

        newRequest.setEventId(newEvent.getId());
        newRequest.setGradingId(cmdJson.getEventGrad());


        newRequest = rs.addRequest(newRequest);

        if(newRequest == null) {
            LogUtil.logger.error("New Request failed");
            ctx.result(gson.toJson(null));
            ctx.status(500);
            return;
        }

        System.out.println(gson.toJson(newRequest));
        ctx.result(gson.toJson(newRequest));
        ctx.status(200);

    };

    public Handler getRequest = (ctx) -> {

        String requestIdString = ctx.pathParam("r_id");
        int requestId = -1;
        try {
            requestId = Integer.parseInt(requestIdString);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }
        if(requestId == -1) {
            LogUtil.logger.error("Bad request ID");
            ctx.result("Bad request ID");
            ctx.status(400);
        }

        Request returnedRequest = rs.getRequest(requestId);

        ctx.result(gson.toJson(returnedRequest));
        ctx.status(200);

    };

    public Handler getAllRequestsByRequesterId = (ctx) -> {

        String requesterIdString = ctx.pathParam("u_id");
        int requesterId = -1;
        try {
            requesterId = Integer.parseInt(requesterIdString);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }
        if(requesterId == -1) {
            LogUtil.logger.error("Bad requester ID");
            ctx.result("Bad requester ID");
            ctx.status(400);
        }

        //The list of requests from this user
        List<Request> requestList = rs.getAllRequestsByRequesterId(requesterId);

        //The list of requests that require this user's attention
        //As a: user:
        //-Get all of this user's requests WHERE status = 11, 21, 23, 31, 33, 35, 37, 40
        List<Request> attnRequestList = rs.getAttnRequestsRequester(requesterId);

        //As a: Supervisor
        //-Get all users that have this user as their supervisor
        List<User> supervisedUserList = us.getUsersBySupervisor(requesterId);
        //-Get all of those users' requests WHERE status = 10-11, 22, 23, 32, 33, 36, 37, 42
        List<Request> attnRequestListSupervisor = new ArrayList<>();
        if(supervisedUserList.size() > 0) {
            for (User user: supervisedUserList) {
                attnRequestListSupervisor.addAll(rs.getAttnRequestsSupervisor(user.getId()));
            }
        }

        //As a: Dept Head
        //Check if the user is a department head
        List<Integer> headOfDepartments = ds.checkIfDeptHead(requesterId);

        System.out.println(headOfDepartments);

        //-Get all users that are a part of the departments this user is the head of
        List<User> departmentHeadUserList = new ArrayList<>();
        if(headOfDepartments.size() > 0) {
            for(Integer departmentNumber: headOfDepartments) {
                departmentHeadUserList.addAll(us.getUsersByDepartment(departmentNumber));
            }
        }
        //-Get all of those users' requests WHERE status = 20-23, 34, 35, 36, 37
        List<Request> attnRequestDeptHead = new ArrayList<>();
        if(departmentHeadUserList.size() > 0) {
            for (User user: departmentHeadUserList) {
                attnRequestDeptHead.addAll(rs.getAttnRequestsDeptHead(user.getId()));
            }
        }

        //As a: BenCo
        //If this user is a BenCo
        User thisUser = us.getUserById(requesterId);

        //-Get all user requests WHERE status = 30-37, 41
        List<Request> attnRequestBenCo = new ArrayList<>();
        if(thisUser.isBenCo()) {
            attnRequestBenCo.addAll(rs.getAttnRequestsBenCo());
        }

        //Place the lists into another list
        List<List> allRequests = new ArrayList<>();
        allRequests.add(requestList);
        allRequests.add(attnRequestList);
        allRequests.add(attnRequestListSupervisor);
        allRequests.add(attnRequestDeptHead);
        allRequests.add(attnRequestBenCo);

        //Edit this
        if(requestList == null) {
            //No results
            requestList = new ArrayList<Request>();
            ctx.result(gson.toJson(allRequests));
            return;
        }

        System.out.println(gson.toJson(requestList));
        ctx.result(gson.toJson(allRequests));
        ctx.status(200);
    };

    public Handler getRemainingReimbursementOfUser = (ctx) -> {
        String requesterIdString = ctx.pathParam("u_id");
        int requesterId = -1;
        try {
            requesterId = Integer.parseInt(requesterIdString);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }
        if(requesterId == -1) {
            LogUtil.logger.error("Bad requester ID");
            ctx.result("Bad requester ID");
            ctx.status(400);
        }

        //The list of requests from this user
        List<Request> requestList = rs.getAllRequestsByRequesterId(requesterId);

        int currentYear = new Date().getYear();

        Calendar startOfYearCal = Calendar.getInstance();
        startOfYearCal.set(currentYear, 1, 1);
        Date startOfYearDate = startOfYearCal.getTime();

        Double reimbursementThisYear = 0.0;

        for (Request request: requestList) {
            if(startOfYearDate.before(new Date(request.getRequestTime()))) {
                reimbursementThisYear += request.getRmbValue().doubleValue();
            }
        }

        Double remainingRMB = Math.max(0, 1000 - reimbursementThisYear);

        ctx.result(gson.toJson(remainingRMB));
        ctx.status(200);

    };


    //Probably going to have to alter this method to handle general updates
    public Handler updateRequestStatusCode = (ctx) -> {
        CMDJsonRequest cmdJson = gson.fromJson(ctx.body(), CMDJsonRequest.class);
        int requestId = cmdJson.getRequestId();
        int newStatus = cmdJson.getRequestState();

        Request updatedRequest = rs.alterRequestStatusCode(requestId, newStatus);

        System.out.println(updatedRequest);

        if(updatedRequest == null) {
            ctx.status(500);
            return;
        }

        ctx.result(gson.toJson(updatedRequest));
        ctx.status(200);
    };

    public Handler addAttachmentAlterStatus = (ctx) -> {

        CMDJson cmdJsonAttachment = gson.fromJson(ctx.body(), CMDJson.class);
        CMDJsonRequest cmdJson = gson.fromJson(ctx.body(), CMDJsonRequest.class);

        Attachment newAttachment = new Attachment();

        newAttachment.setRequestId(cmdJsonAttachment.getRequestId());
        newAttachment.setSatisfied(true);
        newAttachment.setFilepath(cmdJsonAttachment.getFilepath());
        newAttachment.setAddedText(cmdJsonAttachment.getText());

        if(cmdJsonAttachment.getDemandUserId() != 0) {
            newAttachment.setDemanderId(cmdJsonAttachment.getDemandUserId());
        }
        if(cmdJsonAttachment.getSupplyUserId() != 0) {
            newAttachment.setSupplierId(cmdJsonAttachment.getSupplyUserId());
        }

        newAttachment = as.addAttachment(newAttachment);

        if(newAttachment == null) {
            ctx.status(500);
            return;
        }

        int requestId = cmdJson.getRequestId();
        int newStatus = cmdJson.getRequestState();

        Request updatedRequest = rs.alterRequestStatusCode(requestId, newStatus);

        System.out.println(updatedRequest);

        if(updatedRequest == null) {
            ctx.status(500);
            return;
        }

        ctx.result(gson.toJson(updatedRequest));
        ctx.status(200);
    };



}
