package com.remittance.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

}
