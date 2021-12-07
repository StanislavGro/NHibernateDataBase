package com.dataBase.entity;

import javax.persistence.*;

@Entity
@Table(name = "groupp_table")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String group;

    public Group(){}

    public Group(String group){
        this.group = group;
    }

    public Group(int id, String group){
        this.id = id;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", group='" + group + '\'' +
                '}';
    }
}
