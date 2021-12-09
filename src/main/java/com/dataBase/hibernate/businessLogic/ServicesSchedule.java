package com.dataBase.hibernate.businessLogic;

import com.dataBase.entity.Schedule;
import com.dataBase.hibernate.classes.ScheduleDAO;
import com.dataBase.hibernate.interfaces.ServicesAbs;

import java.util.List;

public class ServicesSchedule extends ServicesAbs<Schedule> {

    private ScheduleDAO scheduleDAO = new ScheduleDAO();

    public ServicesSchedule(){}

    @Override
    public void save(Schedule schedule) {
        scheduleDAO.save(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        scheduleDAO.delete(schedule);
    }

    public void delete(int id) {
        scheduleDAO.delete(id);
    }

    @Override
    public void update(Schedule schedule) {
        scheduleDAO.update(schedule);
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleDAO.findAll();
    }

    @Override
    public Schedule findById(int id) {
        return scheduleDAO.findById(id);
    }

}
