package com.revature.repositories;

import com.revature.models.EventCategory;
import com.revature.models.GradeType;
import com.revature.util.HibernateUtil;
import com.revature.util.LogUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class GradeTypeRepoHbImpl implements GradeTypeRepo{
    @Override
    public List<GradeType> getGradeTypes() {

        Session session = HibernateUtil.getSession();
        List<GradeType> gtList = new ArrayList<>();

        try{

            gtList = session.createQuery("FROM GradeType").list();

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return gtList;
    }

    @Override
    public GradeType getGradeTypeById(int id) {

        Session session = HibernateUtil.getSession();

        GradeType returnedGT = null;

        try {

            returnedGT = session.get(GradeType.class, id);

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return returnedGT;
    }
}
