package com.dataBase.hibernate.classes;

import com.dataBase.entity.Auditory;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.interfaces.DAOInterface;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AuditoryDAO implements DAOInterface<Auditory> {


    @Override
    public Auditory findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Auditory.class, id);
    }

    @Override
    public List<Auditory> findAll() {
        List<Auditory> Auditors = (List<Auditory>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From auditory_table").list();
        return Auditors;
    }

    @Override
    public void delete(Auditory auditory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(auditory);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Auditory auditory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(auditory);
        tx1.commit();
        session.close();
    }

    @Override
    public void save(Auditory auditory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(auditory);
        tx1.commit();
        session.close();
    }

}
