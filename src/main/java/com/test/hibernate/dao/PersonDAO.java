package com.test.hibernate.dao;

import com.test.hibernate.HibernateUtils;
import com.test.hibernate.entity.Person;
import com.test.hibernate.entity.PersonInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Random;

public class PersonDAO extends AbstractDAO {
    public void createPerson(Person person) {
        Session session = HibernateUtils.getInstanse().createSession();
        Transaction transaction = session.beginTransaction();

        session.save(person);

        transaction.commit();
        session.close();
    }

    public Person getById(int id) {
        Person person = null;
        Session session = openSession();
        person = session.get(Person.class, id);
        session.close();
        return person;
    }

    public void testCacheLevel1() {
        Session session = openSession();
        start();
        for (int i = 0; i < 2; i++) {
            Person p = session.get(Person.class, 1);
            System.out.println(p);
        }
        stop();
        session.close();
    }


    public void testCacheLevel2() {
        for (int i = 0; i < 2; i++) {
            Person p = getById(1);
            SessionFactory sf = HibernateUtils.getInstanse().getSessionFactory();
            System.out.println(p);

            sf.getCache().evictEntity(Person.class, 1);
        }
    }

    public void testBatchInser() {
        Session session = openSession();
        session.beginTransaction();
        for (int i = 0; i < 100000; i++) {
            session.save(randomPerson());
            if (i % 100 == 0) {
                System.out.println("Inserted " + i);
                session.flush();
                session.clear();
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    private Random random = new Random();

    private Person randomPerson() {
        Person p = new Person();
        p.setFirstName(random.nextFloat() + "");
        return p;

    }

    public static void main(String[] args) {
        PersonDAO dao = new PersonDAO();
        dao.testBatchInser();


    }

    private void savePersonInfo(PersonInfo personInfo) {
        Session session = HibernateUtils.getInstanse().createSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(personInfo);
        transaction.commit();
        session.close();
    }

    private long time;

    private void start() {
        time = System.currentTimeMillis();
    }

    private void stop() {
        long diff = System.currentTimeMillis() - time;
        System.out.println("Time is " + diff + "ms");
    }
}
