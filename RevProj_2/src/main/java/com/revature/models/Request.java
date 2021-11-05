package com.revature.models;

import java.math.BigDecimal;
import java.util.Objects;

public class Request {

    private int id;
    private int requestState;
    private int requesterId;
    private int eventId;
    private int gradingId;
    private BigDecimal rmbValue;
    private long requestTime;
    private long supervisorDeadline;
    private long deptHeadDeadline;

    public Request() {
    }

    public Request(int id, int requestState, int requesterId, int eventId, int gradingId, BigDecimal rmbValue, long requestTime, long supervisorDeadline, long deptHeadDeadline) {
        this.id = id;
        this.requestState = requestState;
        this.requesterId = requesterId;
        this.eventId = eventId;
        this.gradingId = gradingId;
        this.rmbValue = rmbValue;
        this.requestTime = requestTime;
        this.supervisorDeadline = supervisorDeadline;
        this.deptHeadDeadline = deptHeadDeadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRequestState() {
        return requestState;
    }

    public void setRequestState(int requestState) {
        this.requestState = requestState;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getGradingId() {
        return gradingId;
    }

    public void setGradingId(int gradingId) {
        this.gradingId = gradingId;
    }

    public BigDecimal getRmbValue() {
        return rmbValue;
    }

    public void setRmbValue(BigDecimal rmbValue) {
        this.rmbValue = rmbValue;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getSupervisorDeadline() {
        return supervisorDeadline;
    }

    public void setSupervisorDeadline(long supervisorDeadline) {
        this.supervisorDeadline = supervisorDeadline;
    }

    public long getDeptHeadDeadline() {
        return deptHeadDeadline;
    }

    public void setDeptHeadDeadline(long deptHeadDeadline) {
        this.deptHeadDeadline = deptHeadDeadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id && requestState == request.requestState && requesterId == request.requesterId && eventId == request.eventId && gradingId == request.gradingId && requestTime == request.requestTime && supervisorDeadline == request.supervisorDeadline && deptHeadDeadline == request.deptHeadDeadline && Objects.equals((rmbValue.stripTrailingZeros()), (request.rmbValue.stripTrailingZeros()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, requestState, requesterId, eventId, gradingId, rmbValue, requestTime, supervisorDeadline, deptHeadDeadline);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", requestState=" + requestState +
                ", requesterId=" + requesterId +
                ", eventId=" + eventId +
                ", gradingId=" + gradingId +
                ", rmbValue=" + rmbValue +
                ", requestTime=" + requestTime +
                ", supervisorDeadline=" + supervisorDeadline +
                ", deptHeadDeadline=" + deptHeadDeadline +
                '}';
    }
}
