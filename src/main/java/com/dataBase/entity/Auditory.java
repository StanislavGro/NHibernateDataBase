package com.dataBase.entity;

import javax.persistence.*;

@Entity
@Table(name = "auditory_table")
public class Auditory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String auditory;

    public Auditory(){}

    public Auditory(String auditory){
        this.auditory = auditory;
    }

    public Auditory(int id, String auditory){
        this.id = id;
        this.auditory = auditory;
    }

    public int getId() {
        return id;
    }

    public String getAuditory() {
        return auditory;
    }

    public void setAuditory(String auditory) {
        this.auditory = auditory;
    }

    @Override
    public String toString() {
        return "Auditory{" +
                "id=" + id +
                ", auditory='" + auditory + '\'' +
                '}';
    }
}
