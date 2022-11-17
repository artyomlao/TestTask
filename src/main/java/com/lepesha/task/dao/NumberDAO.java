package com.lepesha.task.dao;

import com.lepesha.task.model.Number;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NumberDAO {
    private final SessionFactory sessionFactory;

    @Autowired
    public NumberDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Number number){
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(number);
        tx.commit();
        session.close();
    }

    public void remove(String code) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Number number = new Number();
        number.setCode(code);
        session.delete(number);

        tx.commit();
        session.close();
    }

    public Number getEntity(String code) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        Number number = session.get(Number.class, code);

        tx.commit();
        session.close();

        return number;
    }
}
