package com.dataBase.hibernate.classes;

import com.dataBase.entity.Auditory;
import com.dataBase.entity.Group;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.interfaces.DAOInterface;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GroupDAO implements DAOInterface<Group> {

    @Override
    public Group findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Group.class, id);
    }

    @Override
    public List<Group> findAll() {
        List<Group> Groups = (List<Group>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From groupp_table").list();
        return Groups;
    }

    @Override
    public void delete(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(group);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(group);
        tx1.commit();
        session.close();
    }

    @Override
    public void save(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(group);
        tx1.commit();
        session.close();
    }

}
