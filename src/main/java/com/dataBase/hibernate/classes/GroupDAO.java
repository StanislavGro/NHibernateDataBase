package com.dataBase.hibernate.classes;

import com.dataBase.entity.Group;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.interfaces.DAOImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GroupDAO implements DAOImpl<Group> {

    @Override
    public Group findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Group.class, id);
    }

    @Override
    public List<Group> findAll() {
        List<Group> Groups = (List<Group>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Group").list();
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
        Query query = session.createQuery(
                "select g.id from Group as g ");

        List<Long> idGroup = query.list();

        if(idGroup.size()!=0){
            group.setId(idGroup.get((int)group.getId()-1));
            session.update(group);
        }else {
            System.out.println("Такой группы нет!");
        }
        tx1.commit();
        session.close();
    }

    public void update(Group groupBefore, Group groupAfter) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery(
                "select g.id from Group as g where g.group = '" + groupBefore.getGroup() + "'");

        List<Long> idGroup = query.list();

        if(idGroup.size()!=0){
            groupAfter.setId(idGroup.get(0));
            session.update(groupAfter);
        }else {
            System.out.println("Такой группы нет!");
        }
        tx1.commit();
        session.close();
    }

    @Override
    public void save(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select g.id from Group as g where g.group = '" + group.getGroup() + "'");

        List<Long> idGroup = query.list();

        if(idGroup.size()==0){
            session.save(group);
        }else {
            System.out.println("Ошибка! Такая группа уже есть!!");
        }
        tx1.commit();
        session.close();
    }

}
