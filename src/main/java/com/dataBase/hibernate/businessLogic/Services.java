package com.dataBase.hibernate.businessLogic;

import com.dataBase.entity.Auditory;
import com.dataBase.hibernate.classes.AuditoryDAO;

import java.util.List;

public class Services {

    private AuditoryDAO auditoryDAO = new AuditoryDAO();

    public Services() {}

    public void saveAuditory(Auditory auditory) {
        auditoryDAO.save(auditory);
    }

    public void deleteAuditory(Auditory auditory) {
        auditoryDAO.delete(auditory);
    }

    public void updateAuditory(Auditory auditory) {
        auditoryDAO.update(auditory);
    }

    public List<Auditory> findAllAuditors() {
        return auditoryDAO.findAll();
    }

    public Auditory findAuditoryById(int id) {
        return auditoryDAO.findById(id);
    }

}