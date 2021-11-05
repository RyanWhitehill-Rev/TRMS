package com.revature.repositories;

import com.revature.models.EventCategory;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class EventCategoryRepoHbImpl implements EventCategoryRepo {




    @Override
    public List<EventCategory> getAllEventCategories() {

        Session session = HibernateUtil.getSession();
        List<EventCategory> ecList = new ArrayList<>();

        try{

            ecList = session.createQuery("FROM EventCategory").list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return ecList;
    }

    @Override
    public EventCategory getEventCategoryById(int id) {

        Session session = HibernateUtil.getSession();

        EventCategory returnedEC = null;

        try {

            returnedEC = session.get(EventCategory.class, id);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returnedEC;
    }
}
