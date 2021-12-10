package com.dataBase.hibernate.classes;

import com.dataBase.entity.Auditory;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.interfaces.DAOImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AuditoryDAO implements DAOImpl<Auditory> {


    @Override
    public Auditory findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Auditory.class, id);
    }

    @Override
    public List<Auditory> findAll() {
        List<Auditory> Auditors = (List<Auditory>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Auditory").list();
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
        Query query = session.createQuery(
                "select a.id from Auditory as a ");

        List<Long> idAuditory = query.list();

        if(idAuditory.size()!=0){
            auditory.setId(idAuditory.get((int)auditory.getId()-1));
            session.update(auditory);
        }else {
            System.out.println("Такой аудитории нет!");
        }
        tx1.commit();
        session.close();
    }

    public void update(Auditory auditoryBefore, Auditory auditoryAfter) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select a.id from Auditory as a where a.auditory = '" + auditoryBefore.getAuditory() + "'");

        List<Long> idAuditory = query.list();

        if(idAuditory.size()!=0){
            auditoryAfter.setId(idAuditory.get(0));
            session.update(auditoryAfter);
        }else {
            System.out.println("Такой аудитории нет!");
        }
        tx1.commit();
        session.close();
    }

    @Override
    public void save(Auditory auditory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select au.auditory from Auditory as au where au.auditory = '" + auditory.getAuditory() + "'");

        List<Auditory> auditoryForCheck = query.list();

        if (auditoryForCheck.size() == 0) {
            session.save(auditory);
        }
        else {
            System.out.println("Ошибка!! Такая ау2дитория уже есть!");
        }
        tx1.commit();
        session.close();
    }

}
