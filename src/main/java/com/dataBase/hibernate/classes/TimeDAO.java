package com.dataBase.hibernate.classes;

import com.dataBase.entity.Group;
import com.dataBase.entity.Time;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.interfaces.DAOInterface;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TimeDAO implements DAOInterface<Time> {

    @Override
    public Time findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Time.class, id);
    }

    @Override
    public List<Time> findAll() {
        List<Time> Times = (List<Time>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Time").list();
        return Times;
    }

    @Override
    public void delete(Time time) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(time);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Time time) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(time);
        tx1.commit();
        session.close();
    }

    @Override
    public void save(Time time) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(time);
        tx1.commit();
        session.close();
    }
}
