package com.dataBase.hibernate;

import com.dataBase.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;

//Данный класс будет считывать все то, что находится в hibernate.cfg.xml
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory; //настройка и работа с сессиями (фабрика сессий)

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Schedule.class);
                configuration.addAnnotatedClass(Day.class);
                configuration.addAnnotatedClass(Time.class);
                configuration.addAnnotatedClass(Group.class);
                configuration.addAnnotatedClass(Auditory.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("ССАНИНА!" + e);
            }
        }
        return sessionFactory;
    }
}
