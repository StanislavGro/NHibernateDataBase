package com.dataBase.hibernate.interfaces;

import java.util.List;

public interface DAOInterface<Type> {

    Type findById(int id);
    List<Type> findAll();
    void delete(Type object);
    void update(Type object);
    void save(Type object);

}
