package com.dataBase.hibernate.businessLogic;

import com.dataBase.entity.Group;
import com.dataBase.hibernate.HibernateSessionFactoryUtil;
import com.dataBase.hibernate.classes.GroupDAO;
import com.dataBase.hibernate.interfaces.ServicesAbs;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ServicesGroup extends ServicesAbs<Group> {

    private GroupDAO groupDAO = new GroupDAO();

    public ServicesGroup(){}

    @Override
    public void save(Group group) {
        groupDAO.save(group);
    }

    @Override
    public void delete(Group group) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("select g.id " +
                "from Group as g " +
                "where g.group = '" + group.getGroup()+"'");

        List id = query.list();
        if(id.size()!=0){
            group.setId((long)id.get(0));
            groupDAO.delete(group);
        }
        else {
            System.out.println("Такой группы нет!");
        }


        tx1.commit();
        session.close();
    }

    @Override
    public void update(Group group) {
        groupDAO.update(group);
    }

    @Override
    public List<Group> findAll() {
        return groupDAO.findAll();
    }

    @Override
    public Group findById(int id) {
        return groupDAO.findById(id);
    }
}
