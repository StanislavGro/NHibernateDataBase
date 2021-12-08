package com.dataBase.hibernate.businessLogic;

import com.dataBase.entity.*;
import com.dataBase.hibernate.classes.*;

import java.util.List;

public class Services {

    private AuditoryDAO auditoryDAO = new AuditoryDAO();
    private GroupDAO groupDAO = new GroupDAO();
    private DayDAO dayDAO = new DayDAO();
    private TimeDAO timeDAO = new TimeDAO();
    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    public Services() {}

    //АУДИТОРИЯ

    public void saveAuditory(Auditory auditory) {
        auditoryDAO.save(auditory);
    }

    public void deleteAuditory(Auditory auditory) {
        auditoryDAO.delete(auditory);
    }

    public void updateAuditory(Auditory auditory) {
        auditoryDAO.update(auditory);
    }

    public List<Auditory> findAllAuditors() {
        return auditoryDAO.findAll();
    }

    public Auditory findAuditoryById(int id) {
        return auditoryDAO.findById(id);
    }

    //ГРУППА

    public void saveGroup(Group group) {
        groupDAO.save(group);
    }

    public void deleteGroup(Group group) {
        groupDAO.delete(group);
    }

    public void updateGroup(Group group) {
        groupDAO.update(group);
    }

    public List<Group> findAllGroup() {
        return groupDAO.findAll();
    }

    public Group findGroupById(int id) {
        return groupDAO.findById(id);
    }

    //ВРЕМЯ

    public void saveTime(Time time) {
        timeDAO.save(time);
    }

    public void deleteTime(Time time) {
        timeDAO.delete(time);
    }

    public void updateTime(Time time) {
        timeDAO.update(time);
    }

    public List<Time> findAllTime() {
        return timeDAO.findAll();
    }

    public Time findTimeById(int id) {
        return timeDAO.findById(id);
    }

    //ГРУППЫ

    public void saveDay(Day day) {
        dayDAO.save(day);
    }

    public void deleteDay(Day day) {
        dayDAO.delete(day);
    }

    public void updateDay(Day day) {
        dayDAO.update(day);
    }

    public List<Day> findAllDay() {
        return dayDAO.findAll();
    }

    public Day findDayById(int id) {
        return dayDAO.findById(id);
    }

}