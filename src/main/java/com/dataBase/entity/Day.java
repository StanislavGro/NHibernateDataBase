package com.dataBase.entity;

import javax.persistence.*;

@Entity
@Table(name = "day_table")
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String day;

    public Day(){}

    public Day(String day){
        this.day = day;
    }

    public Day(int id, String day){
        this.id = id;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
