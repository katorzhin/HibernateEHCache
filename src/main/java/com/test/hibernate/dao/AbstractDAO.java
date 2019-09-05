package com.test.hibernate.dao;

import com.test.hibernate.HibernateUtils;
import org.hibernate.Session;

public class AbstractDAO {
    public Session openSession(){
        return HibernateUtils.getInstanse().createSession();

    }
}
