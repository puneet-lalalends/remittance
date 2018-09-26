package com.remittance.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CountryDetailsOperation {

    public boolean save(CountryDetails countryDetails){

        Session session = DomainFactory.getSession();
        Transaction tx2 = session.beginTransaction();
        try {
            session.save(countryDetails);
            tx2.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx2.commit();
        }
        return true;
    }

    public List reteriveAll(){
        List objectsList = null;
        Session session = DomainFactory.getSession();
        Transaction tx2 = session.beginTransaction();
        try {
            Query query = session.createQuery("from "+CountryDetails.class.getName());
            objectsList = query.list();
            tx2.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx2.commit();
        }finally {
            session.close();
        }
        return objectsList;
    }


}
