package com.remittance.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class PartnerInfoOperation {

    public boolean save(PartnerInfo partnerInfo){

        Session session = DomainFactory.getSession();
        Transaction tx2 = session.beginTransaction();
        try {
            session.save(partnerInfo);
            tx2.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx2.commit();
        }
        return true;
    }

    public List reteriveSpecific(PartnerInfo partnerInfo){
        Session session = DomainFactory.getSession();
        Transaction tx2 = session.beginTransaction();
        List objectsList = null;
        try {
            String hql = "FROM PartnerInfo pinfo WHERE  pinfo.partner = '" + partnerInfo.getPartner() +"'";
            Query query = session.createQuery(hql);
            objectsList = query.list();
            tx2.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx2.commit();
        }
        return objectsList;
    }


}
