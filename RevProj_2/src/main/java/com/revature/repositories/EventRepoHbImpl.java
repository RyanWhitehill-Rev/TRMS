package com.revature.repositories;

import com.revature.models.Event;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import com.revature.util.LogUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EventRepoHbImpl implements EventRepo{

    @Override
    public Event getEventById(int id) {

        Session session = HibernateUtil.getSession();

        Event returnedEvent = null;

        try {

            returnedEvent = session.get(Event.class, id);

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return returnedEvent;
    }

    @Override
    public Event addEvent(Event event) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            event.setId((int)session.save(event));

            session.getTransaction().commit();
        } catch (HibernateException e) {
            LogUtil.logger.error(e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return event;
    }
}
