package com.revature.repositories;

import com.revature.models.GradeType;
import com.revature.models.RequestState;
import com.revature.util.HibernateUtil;
import com.revature.util.LogUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class RequestStateRepoHbImpl implements RequestStateRepo{
    @Override
    public RequestState getRequestStateById(int id) {

        Session session = HibernateUtil.getSession();

        RequestState returnedRS = null;

        try {

            returnedRS = session.get(RequestState.class, id);

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return returnedRS;
    }
}
