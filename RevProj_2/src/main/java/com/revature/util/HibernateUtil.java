package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

    //Abstract away creating the SessionFactory and the process of creating sessions from that factory

    private static SessionFactory sf = new Configuration().configure().buildSessionFactory();

    public static Session getSession() {
        return sf.openSession();
    }

}
