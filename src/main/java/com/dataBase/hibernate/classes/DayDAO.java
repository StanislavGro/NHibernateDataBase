package com.dataBase.hibernate.classes;

import com.dataBase.entity.Day;
import com.dataBase.entity.Time;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.interfaces.DAOInterface;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DayDAO implements DAOInterface<Day> {
    @Override
    public Day findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Day.class, id);
    }

    @Override
    public List<Day> findAll() {
        List<Day> Days = (List<Day>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Day").list();
        return Days;
    }

    @Override
    public void delete(Day day) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(day);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Day day) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(day);
        tx1.commit();
        session.close();
    }

    @Override
    public void save(Day day) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(day);
        tx1.commit();
        session.close();
    }
}
