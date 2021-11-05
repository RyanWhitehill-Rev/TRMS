package com.revature.models;

public class CMDJsonRequest {

    private int requestId;
    private int requesterId;
    private int supervisorId;
    private int deptHeadId;
    private int requestState;

    public CMDJsonRequest() {
    }

    public CMDJsonRequest(int requestId, int requesterId, int supervisorId, int deptHeadId, int requestState) {
        this.requestId = requestId;
        this.requesterId = requesterId;
        this.supervisorId = supervisorId;
        this.deptHeadId = deptHeadId;
        this.requestState = requestState;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public int getDeptHeadId() {
        return deptHeadId;
    }

    public void setDeptHeadId(int deptHeadId) {
        this.deptHeadId = deptHeadId;
    }

    public int getRequestState() {
        return requestState;
    }

    public void setRequestState(int requestState) {
        this.requestState = requestState;
    }
}
