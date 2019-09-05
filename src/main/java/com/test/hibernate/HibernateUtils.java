package com.test.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    private SessionFactory sessionFactory;

    private static HibernateUtils instanse = new HibernateUtils();

    private HibernateUtils() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    public static HibernateUtils getInstanse() {
        return instanse;
    }

    public Session createSession() {
        return sessionFactory.openSession();
    }

}
