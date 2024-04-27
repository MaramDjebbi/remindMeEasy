package com.example.remindmeeasy.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName = "reminders")
public class reminder {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "dateTime")
    private Date dateTime;

    @ColumnInfo(name = "repeat")
    private boolean repeat;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "status")
    private String status;

    @ColumnInfo(name = "category")
    private String category;




    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
//    public reminder(String name, String description, Date dateTime, boolean repeat, int priority, String status, String category) {
//        this.name = name;
//        this.description = description;
//        this.dateTime = dateTime;
//        this.repeat = repeat;
//        this.priority = priority;
//        this.status = status;
//        this.category = category;
//    }
}

