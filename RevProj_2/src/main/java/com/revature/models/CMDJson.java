package com.revature.models;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CMDJson {

    //Generic info
    private int id;

    //Event info
    private BigInteger eventDate;
    private Long eventTime;
    private String eventLoca;
    private BigDecimal eventCost;
    private String eventDesc;
    private String eventJust;
    private int eventGrad;
    private int eventType;

    //Attachment Info

    private int requestId;
    private int supplyUserId;
    private int demandUserId;
    private boolean satisfied;
    private String filepath;
    private String text;
    private BigDecimal rmbValue;


    public CMDJson() {
    }

    public CMDJson(int id, BigInteger eventDate, Long eventTime, String eventLoca, BigDecimal eventCost, String eventDesc, String eventJust, int eventGrad, int eventType, int requestId, int supplyUserId, int demandUserId, boolean satisfied, String filepath, String text, BigDecimal rmbValue) {
        this.id = id;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLoca = eventLoca;
        this.eventCost = eventCost;
        this.eventDesc = eventDesc;
        this.eventJust = eventJust;
        this.eventGrad = eventGrad;
        this.eventType = eventType;
        this.requestId = requestId;
        this.supplyUserId = supplyUserId;
        this.demandUserId = demandUserId;
        this.satisfied = satisfied;
        this.filepath = filepath;
        this.text = text;
        this.rmbValue = rmbValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getEventDate() {
        return eventDate;
    }

    public void setEventDate(BigInteger eventDate) {
        this.eventDate = eventDate;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLoca() {
        return eventLoca;
    }

    public void setEventLoca(String eventLoca) {
        this.eventLoca = eventLoca;
    }

    public BigDecimal getEventCost() {
        return eventCost;
    }

    public void setEventCost(BigDecimal eventCost) {
        this.eventCost = eventCost;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public int getEventGrad() {
        return eventGrad;
    }

    public void setEventGrad(int eventGrad) {
        this.eventGrad = eventGrad;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getEventJust() {
        return eventJust;
    }

    public void setEventJust(String eventJust) {
        this.eventJust = eventJust;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getSupplyUserId() {
        return supplyUserId;
    }

    public void setSupplyUserId(int supplyUserId) {
        this.supplyUserId = supplyUserId;
    }

    public int getDemandUserId() {
        return demandUserId;
    }

    public void setDemandUserId(int demandUserId) {
        this.demandUserId = demandUserId;
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public void setSatisfied(boolean satisfied) {
        this.satisfied = satisfied;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getRmbValue() {
        return rmbValue;
    }

    public void setRmbValue(BigDecimal rmbValue) {
        this.rmbValue = rmbValue;
    }
}
