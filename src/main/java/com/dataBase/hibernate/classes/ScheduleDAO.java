package com.dataBase.hibernate.classes;

import com.dataBase.entity.*;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.businessLogic.ServicesAuditory;
import com.dataBase.hibernate.businessLogic.ServicesGroup;
import com.dataBase.hibernate.interfaces.DAOImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ScheduleDAO implements DAOImpl<Schedule> {



    @Override
    public Schedule findById(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Schedule schedule = session.get(Schedule.class, id);
        session.close();
        return schedule;
    }

    @Override
    public List<Schedule> findAll() {
        List<Schedule> Schedules = (List<Schedule>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Schedule").list();
        return Schedules;
    }

    @Override
    public void delete(Schedule schedule) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery("select a.id, g.id " +
                "from Auditory as a " +
                "inner join Group as g on a.auditory = '" + schedule.getAuditory().getAuditory() +
                "' and g.group = '" + schedule.getGroup().getGroup()+"'");

        List id = query.list();

        if(id.size()!=0){
            schedule.getAuditory().setId((long)id.get(0));
            schedule.getAuditory().setId((long)id.get(1));
        }

        query = session.createQuery(
                "select sc.id " +
                        "from Schedule as sc " +
                        "join Day as d on d.id = sc.day " +
                        "join Time as ti on ti.id = sc.time " +
                        "join Group as gr on gr.id = sc.group " +
                        "join Auditory as au on au.id = sc.auditory " +
                        "where sc.week = " + schedule.getWeek() + " and " +
                        "d.day = '" + schedule.getDay().getDay() + "' and " +
                        "ti.timeStart = '" + schedule.getTime().getTimeStart() + "' and " +
                        "ti.timeEnd = '" + schedule.getTime().getTimeEnd() + "' and " +
                        "gr.group = '" + schedule.getGroup().getGroup() + "' and " +
                        "au.auditory = '" + schedule.getAuditory().getAuditory() + "'");

        List idSchedule = query.list();

        if(idSchedule.size()!=0){
            schedule.setId((long)idSchedule.get(0));

            session.delete(schedule);
        }else {
            System.out.println("Такого расписания нет!");
        }
        tx1.commit();
        session.close();
    }

    public void delete(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Schedule schedule;

        Query query = session.createQuery(
                "select sc.id from Schedule as sc ");

        List<Long> idSchedule = query.list();

        if(idSchedule.size()!=0){
            schedule = findById(idSchedule.get(id-1));
            session.delete(schedule);
        }else {
            System.out.println("Такого расписания нет!");
        }

        tx1.commit();
        session.close();
    }


    @Override
    public void update(Schedule schedule) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select sc.id from Schedule as sc ");

        List<Long> idSchedule = query.list();

        if(idSchedule.size()!=0){
            Schedule sc = findById(idSchedule.get((int)schedule.getId()-1));

            sc.setDay(schedule.getDay());
            sc.setTime(schedule.getTime());
            sc.setWeek(schedule.getWeek());

            query = session.createQuery("select a.id " +
                    "from Auditory as a where a.auditory = '" + schedule.getAuditory().getAuditory() + "'");

            List<Long> idAuditory = query.list();

            if(idAuditory.size()==0){
                ServicesAuditory servicesAuditory = new ServicesAuditory();
                Auditory auditory = new Auditory(schedule.getAuditory().getAuditory());
                servicesAuditory.save(auditory);
                sc.setAuditory(auditory);
            }else {
                sc.getAuditory().setId(idAuditory.get(0));
            }

            query = session.createQuery("select g.id " +
                    "from Group as g where g.group = '" + schedule.getGroup().getGroup() + "'");

            List<Long> idGroup = query.list();

            if(idGroup.size()==0){
                ServicesGroup servicesGroup = new ServicesGroup();
                Group group = new Group(schedule.getGroup().getGroup());
                servicesGroup.save(group);
                sc.setGroup(group);
            }else {
                sc.getGroup().setId(idGroup.get(0));
            }

            //schedule.setAuditory(auditory);
            session.update(sc);
        }else {
            System.out.println("Таблица расписаний пуста!");
        }

        tx1.commit();
        session.close();
    }

    public void update(int id, int weekNumber) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select sc.id from Schedule as sc ");

        List<Long> idSchedule = query.list();

        if(idSchedule.size()!=0){
            Schedule schedule = findById(idSchedule.get(id-1));

            schedule.setWeek(weekNumber);
            session.update(schedule);
        }else {
            System.out.println("Таблица расписаний пуста!");
        }

        tx1.commit();
        session.close();
    }


    public void findByTime(String startTime, String endTime) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(
                "SELECT S FROM Schedule S " +
                        "INNER JOIN Time T on T.id = S.time " +
                        "WHERE T.timeStart = '" + startTime + "' " +
                        "AND T.timeEnd = '" + endTime + "' " +
                        "ORDER BY S.auditory, S.week, S.day"
        );
        List<Schedule> list = query.list();
        session.close();

        System.out.println();
        for (int i = 0; i < list.size();) {
            Schedule schedule = list.get(i);
            System.out.println(schedule.getAuditory().getAuditory() + " аудитория занята в следующие недели и дни:");
            for (int j = i; j < list.size(); j++) {
                /*if (j == list.size() - 1) {
                    i = j;
                    break;
                }*/
                if (schedule.getAuditory().getAuditory().compareTo(list.get(j).getAuditory().getAuditory()) == 0) {
                    System.out.print(list.get(j).getWeek() + " неделя: " + list.get(j).getDay().getDay());
                    for (int k = j + 1; k < list.size(); k++) {
                        if (list.get(j).getWeek() == list.get(k).getWeek() &&
                                list.get(j).getAuditory().getAuditory().compareTo(list.get(k).getAuditory().getAuditory()) == 0)
                            System.out.print(" " + list.get(k).getDay().getDay());
                        else {
                            System.out.println();
                            j = k - 1;
                            break;
                        }
                        if (k == list.size() - 1) j++;
                    }
                }
                else {
                    System.out.println();
                    i = j;
                    break;
                }
                i = j;
                if (j == list.size() - 1) i++;
            }
//            if (i == list.size() - 1) break;
        }
        System.out.println("\n\nОстальные аудитории свободны в любое время");
    }

    public void findByNumberOfHours(int numberOfHours, int week) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery(
                "SELECT S FROM Schedule S " +
                        "WHERE S.week = " + week +
                        " ORDER BY S.auditory, S.day"
        );
        List<Schedule> list = query.list();
        session.close();

        int amountOfLessons = (int) Math.ceil(((double) numberOfHours * 60) / 90);
        int maxAmountOfLessons = 42 - amountOfLessons;

        for (int i = 0; i < list.size(); i++) {
            Schedule schedule = list.get(i);
            System.out.println("\nНа " + week + " неделе " +
                    schedule.getAuditory().getAuditory() + " аудитория занята в следующие дни и время:");
            for (int j = i; j < list.size(); j++) {
                if (schedule.getAuditory().getAuditory().compareTo(list.get(j).getAuditory().getAuditory()) == 0) {
                    maxAmountOfLessons--;
                    System.out.print(list.get(j).getDay().getDay() + ": "
                            + list.get(j).getTime().getTimeStart() + " - " + list.get(j).getTime().getTimeEnd());
                    for (int k = j + 1; k < list.size(); k++) {
                        if (list.get(j).getDay().getDay().compareTo(list.get(k).getDay().getDay()) == 0 &&
                                list.get(j).getAuditory().getAuditory().compareTo(list.get(k).getAuditory().getAuditory()) == 0) {
                            maxAmountOfLessons--;
                            System.out.print(" " + list.get(k).getTime().getTimeStart() +
                                    " - " + list.get(k).getTime().getTimeEnd());
                        }
                        else {
                            j = k - 1;
                            break;
                        }
                        if (k == list.size() - 1) j++;
                    }
                    System.out.println();
                }
                else {
                    i = j - 1;
                    break;
                }
                if (j == list.size() - 1) i++;
            }
            if (maxAmountOfLessons < 0)
                System.out.println("Данная аудитория не подойдет " + amountOfLessons + " занятий");
            else
                System.out.println("Данная аудитория подойдет для " + amountOfLessons + " занятий");
            maxAmountOfLessons = 42 - amountOfLessons;
        }
        System.out.println("\n\nОстальные аудитории свободны в любое время");
    }


    public void update(int id, Day day) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select sc.id from Schedule as sc ");

        List<Long> idSchedule = query.list();

        if(idSchedule.size()!=0){
            Schedule schedule = findById(idSchedule.get(id-1));

            schedule.setDay(day);

            //schedule.setAuditory(auditory);
            session.update(schedule);
        }else {
            System.out.println("Таблица расписаний пуста!");
        }

        tx1.commit();
        session.close();
    }


    public void update(int id, Time time) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select sc.id from Schedule as sc ");

        List<Long> idSchedule = query.list();

        if(idSchedule.size()!=0){
            Schedule schedule = findById(idSchedule.get(id-1));

            schedule.setTime(time);

            //schedule.setAuditory(auditory);
            session.update(schedule);
        }else {
            System.out.println("Таблица расписаний пуста!");
        }

        tx1.commit();
        session.close();
    }


    public void update(Auditory auditory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select sc.id from Schedule as sc ");

        List<Long> idSchedule = query.list();

        if(idSchedule.size()!=0){
            Schedule schedule = findById(idSchedule.get((int)auditory.getId()-1));

            query = session.createQuery("select a.id " +
                    "from Auditory as a where a.auditory = '" + auditory.getAuditory() + "'");

            List<Long> idAuditory = query.list();

            if(idAuditory.size()==0){
                ServicesAuditory servicesAuditory = new ServicesAuditory();
                Auditory auditory1 = new Auditory(auditory.getAuditory());
                servicesAuditory.save(auditory1);
                schedule.setAuditory(auditory1);
            }else {
                schedule.getAuditory().setId(idAuditory.get(0));
            }

            //schedule.setAuditory(auditory);
            session.update(schedule);
        }else {
            System.out.println("Таблица расписаний пуста!");
        }

        tx1.commit();
        session.close();
    }


    public void update(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select sc.id from Schedule as sc ");

        List<Long> idSchedule = query.list();

        if(idSchedule.size()!=0){
            Schedule schedule = findById(idSchedule.get((int)group.getId()-1));

            query = session.createQuery("select g.id " +
                    "from Group as g where g.group = '" + group.getGroup() + "'");

            List<Long> idGroup = query.list();

            if(idGroup.size()==0){
                ServicesGroup servicesGroup = new ServicesGroup();
                group.setId(0L);
                servicesGroup.save(group);
                schedule.setGroup(group);
            }else {
                schedule.getGroup().setId(idGroup.get(0));
            }

            //schedule.setAuditory(auditory);
            session.update(schedule);
        }else {
            System.out.println("Таблица расписаний пуста!");
        }

        tx1.commit();
        session.close();
    }

    @Override
    public void save(Schedule schedule) {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery("select a.id " +
                "from Auditory as a " +
                "where a.auditory = '" + schedule.getAuditory().getAuditory() +"'");

        List<Long> idAuditory = query.list();

        query = session.createQuery("select g.id " +
                "from Group as g where g.group = '" + schedule.getGroup().getGroup()+"'");

        List<Long> idGroup = query.list();

        if(idAuditory.size()==0){
            ServicesAuditory servicesAuditory = new ServicesAuditory();
            servicesAuditory.save(schedule.getAuditory());
        }else {
            schedule.getAuditory().setId(idAuditory.get(0));
        }

        if(idGroup.size()==0){
            ServicesGroup servicesGroup = new ServicesGroup();
            servicesGroup.save(schedule.getGroup());
        }else {
            schedule.getGroup().setId(idGroup.get(0));
        }

        query = session.createQuery(
                "select sc.week, d.day, ti.timeStart, ti.timeEnd, gr.group, au.auditory " +
                        "from Schedule as sc " +
                        "join Day as d on d.id = sc.day " +
                        "join Time as ti on ti.id = sc.time " +
                        "join Group as gr on gr.id = sc.group " +
                        "join Auditory as au on au.id = sc.auditory " +
                        "where sc.week = " + schedule.getWeek() + " and " +
                        "d.day = '" + schedule.getDay().getDay() + "' and " +
                        "ti.timeStart = '" + schedule.getTime().getTimeStart() + "' and " +
                        "ti.timeEnd = '" + schedule.getTime().getTimeEnd() + "' and " +
                        "gr.group = '" + schedule.getGroup().getGroup() + "' and " +
                        "au.auditory = '" + schedule.getAuditory().getAuditory() + "'");

        List idSchedule = query.list();

        if(idSchedule.size()==0){
            session.save(schedule);
        }else {
            System.out.println("Такое расписание уже есть!");
        }

        tx1.commit();
        session.close();
    }
}
