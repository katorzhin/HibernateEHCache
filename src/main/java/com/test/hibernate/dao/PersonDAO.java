package com.test.hibernate.dao;

import com.test.hibernate.HibernateUtils;
import com.test.hibernate.entity.Person;
import com.test.hibernate.entity.PersonInfo;
import org.hibernate.Session;
import org.hibernate.Transaction;

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

    private void start() {
        time = System.currentTimeMillis();
    }

    private long time;

    private void stop() {
        long diff = System.currentTimeMillis() - time;
        System.out.println("Time is " + diff + "ms");
    }

    public static void main(String[] args) {
        PersonDAO dao = new PersonDAO();
        dao.testCacheLevel1();

//        for (int i = 0; i < 2; i++) {
//            Person p = dao.getById(1);
//            System.out.println(p);
//        }


//        Person person = new Person();
//        person.setFirstName("оля");
//        dao.createPerson(person);
//
//        PersonInfo personInfo = new PersonInfo();
//        personInfo.setPassportNumber("ВК124567");
//        person.setPersonInfo(personInfo);
//        dao.createPerson(person);
    }

    private void savePersonInfo(PersonInfo personInfo) {
        Session session = HibernateUtils.getInstanse().createSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(personInfo);
        transaction.commit();
        session.close();


    }
}
