package com.dataBase.hibernate.businessLogic;

import com.dataBase.entity.Auditory;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.classes.AuditoryDAO;
import com.dataBase.hibernate.interfaces.ServicesAbs;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServicesAuditory extends ServicesAbs<Auditory> {

    private AuditoryDAO auditoryDAO = new AuditoryDAO();

    public ServicesAuditory(){}

    @Override
    public void save(Auditory auditory) {
        auditoryDAO.save(auditory);
    }

    @Override
    public void delete(Auditory auditory) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select a.id " +
                "from Auditory as a where a.auditory = '" + auditory.getAuditory()+"'");

        List id = query.list();
        if(id.size()!=0){
            auditory.setId((long)id.get(0));
            auditoryDAO.delete(auditory);
        }
        else {
            System.out.println("Такой аудитории нет!");
        }

        tx1.commit();
        session.close();
    }

    @Override
    public void update(Auditory auditory) {
        auditoryDAO.update(auditory);
    }

    @Override
    public List<Auditory> findAll() {
        return auditoryDAO.findAll();
    }

    @Override
    public Auditory findById(int id) {
        return auditoryDAO.findById(id);
    }
}
