package com.example.remindmeeasy.model;

import java.util.Date;

public class reminder {
    private String name;
    private String description;
    private Date dateTime;
    private boolean repeat;
    private int priority;
    private String status;
    private String category;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public int getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }



    // Constructor
    public reminder(String name, String description, Date dateTime, boolean repeat, int priority, String status, String category) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.repeat = repeat;
        this.priority = priority;
        this.status = status;
        this.category = category;
    }
}

