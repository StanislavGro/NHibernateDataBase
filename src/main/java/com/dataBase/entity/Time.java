package com.dataBase.entity;

import javax.persistence.*;

@Entity
@Table(name = "time_table")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String timeStart;
    private String timeEnd;

    public Time(){}

    public Time(String timeStart, String timeEnd){
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Time(int id, String timeStart, String timeEnd){
        this.id = id;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }
    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", timeStart='" + timeStart + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                '}';
    }


    public int getId() {
        return id;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

}
