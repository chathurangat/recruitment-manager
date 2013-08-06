package com.hsenidmobile.recruitment.model;

/**
 * Created with IntelliJ IDEA.
 * User: tharanga
 * Date: 7/31/13
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class Calendar {
    private String id;
    private String title;
    private String date;
    private String startTime;
    private String endTime;
    private String description;
    public  String setStartTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  String getTitle(){
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public  String getStartTime(){
        return startTime;
    }
    public void setStartTime(String startTime)
    {
        this.startTime =startTime;
    }
    public String getEndTime(){
        return endTime;
    }
    public void setEndTime(String endTime){
        this.endTime=endTime;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description=description;
    }
    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date=date;
    }


}
