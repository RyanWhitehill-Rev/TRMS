package com.revature.models;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Event {

    private int id;
    private int eventTypeId;
    private BigDecimal eventCost;
    private Long eventTime;
    private String eventLocation;
    private String description;
    private String justification;

    public Event() {
    }

    public Event(int id, int eventTypeId, BigDecimal eventCost, Long eventTime, String eventLocation, String description, String justification) {
        this.id = id;
        this.eventTypeId = eventTypeId;
        this.eventCost = eventCost;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.description = description;
        this.justification = justification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public BigDecimal getEventCost() {
        return eventCost;
    }

    public void setEventCost(BigDecimal eventCost) {
        this.eventCost = eventCost;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id && eventTypeId == event.eventTypeId && Objects.equals(eventCost.stripTrailingZeros(), event.eventCost.stripTrailingZeros()) && Objects.equals(eventTime, event.eventTime) && Objects.equals(eventLocation, event.eventLocation) && Objects.equals(description, event.description) && Objects.equals(justification, event.justification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eventTypeId, eventCost, eventTime, eventLocation, description, justification);
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", eventTypeId=" + eventTypeId +
                ", eventCost=" + eventCost +
                ", eventTime=" + eventTime +
                ", eventLocation='" + eventLocation + '\'' +
                ", description='" + description + '\'' +
                ", justification='" + justification + '\'' +
                '}';
    }
}
