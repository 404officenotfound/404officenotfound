package com.office.notfound.event.model.dto;

public class EventDTO {

    private int eventCode;
    private String eventTitle;
    private String eventContent;
    private String eventImg;
    private String eventStatus;

    public EventDTO() {
    }

    public EventDTO(int eventCode, String eventTitle, String eventContent, String eventImg, String eventStatus) {
        this.eventCode = eventCode;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.eventImg = eventImg;
        this.eventStatus = eventStatus;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventImg() {
        return eventImg;
    }

    public void setEventImg(String eventImg) {
        this.eventImg = eventImg;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "eventCode=" + eventCode +
                ", eventTitle='" + eventTitle + '\'' +
                ", eventContent='" + eventContent + '\'' +
                ", eventImg='" + eventImg + '\'' +
                ", eventStatus='" + eventStatus + '\'' +
                '}';
    }
}
