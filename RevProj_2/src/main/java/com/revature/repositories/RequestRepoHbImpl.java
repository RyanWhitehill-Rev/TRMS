package com.revature.repositories;

import com.revature.models.Request;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class RequestRepoHbImpl implements RequestRepo{

    @Override
    public Request addRequest(Request requestIn) {
        Session session = HibernateUtil.getSession();

        System.out.println("Adding request");

        try {
            session.beginTransaction();

            requestIn.setId((int)session.save(requestIn));

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return requestIn;
    }

    @Override
    public Request getRequest(int id) {

        Session session = HibernateUtil.getSession();
        Request returnedRequest = null;

        try {
            session.beginTransaction();
            returnedRequest = session.get(Request.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }

        return returnedRequest;
    }

    @Override
    public List<Request> getAllRequests() {
        Session session = HibernateUtil.getSession();
        List<Request> requestList = new ArrayList<>();

        try{

            requestList = session.createQuery("FROM Request").list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return requestList;
    }

    @Override
    public Request updateRequest(Request requestIn) {
        Session session = HibernateUtil.getSession();

        try{
            session.beginTransaction();

            session.update(requestIn);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }

        return requestIn;
    }

    @Override
    public Request deleteRequest(int id) {
        Session session = HibernateUtil.getSession();

        Request deletedRequest;

        try{

            session.beginTransaction();

            deletedRequest = session.get(Request.class, id);

            session.delete(deletedRequest);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }

        return deletedRequest;
    }

    @Override
    public List<Request> getAllRequestsByRequesterId(int requesterId) {
        Session session = HibernateUtil.getSession();
        List<Request> returnedRequests = null;

        try{

            Criteria crit = session.createCriteria(Request.class);
            crit.add(Restrictions.eq("requesterId", requesterId));

            returnedRequests = crit.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returnedRequests;
    }

    @Override
    public List<Request> getAttnRequestsRequester(int requesterId) {
        Session session = HibernateUtil.getSession();
        List<Request> returnedRequests = null;

        try{

            Criteria crit = session.createCriteria(Request.class);
            //-Get all of this user's requests WHERE status = 11, 21, 23, 31, 33, 35, 37, 40
            Disjunction status = Restrictions.disjunction();
            status.add(Restrictions.eq("requestState", 11));
            status.add(Restrictions.eq("requestState", 21));
            status.add(Restrictions.eq("requestState", 23));
            status.add(Restrictions.eq("requestState", 31));
            status.add(Restrictions.eq("requestState", 33));
            status.add(Restrictions.eq("requestState", 35));
            status.add(Restrictions.eq("requestState", 37));
            status.add(Restrictions.eq("requestState", 40));

            crit.add(Restrictions.eq("requesterId", requesterId));
            crit.add(status);

            returnedRequests = crit.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returnedRequests;

    }

    @Override
    public List<Request> getAttnRequestsSupervisor(int requesterId) {
        Session session = HibernateUtil.getSession();
        List<Request> returnedRequests = null;

        try{

            Criteria crit = session.createCriteria(Request.class);
            //-Get all of those users' requests WHERE status = 10-11, 22, 23, 32, 33, 36, 37, 42
            Disjunction status = Restrictions.disjunction();
            status.add(Restrictions.eq("requestState", 10));
            status.add(Restrictions.eq("requestState", 11));
            status.add(Restrictions.eq("requestState", 22));
            status.add(Restrictions.eq("requestState", 23));
            status.add(Restrictions.eq("requestState", 32));
            status.add(Restrictions.eq("requestState", 33));
            status.add(Restrictions.eq("requestState", 36));
            status.add(Restrictions.eq("requestState", 37));
            status.add(Restrictions.eq("requestState", 43));

            crit.add(Restrictions.eq("requesterId", requesterId));
            crit.add(status);

            returnedRequests = crit.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returnedRequests;
    }

    @Override
    public List<Request> getAttnRequestsDeptHead(int requesterId) {
        Session session = HibernateUtil.getSession();
        List<Request> returnedRequests = null;

        try{

            Criteria crit = session.createCriteria(Request.class);
            //-Get all of those users' requests WHERE status = 20-23, 34, 35, 36, 37
            Disjunction status = Restrictions.disjunction();
            status.add(Restrictions.eq("requestState", 20));
            status.add(Restrictions.eq("requestState", 21));
            status.add(Restrictions.eq("requestState", 22));
            status.add(Restrictions.eq("requestState", 23));
            status.add(Restrictions.eq("requestState", 34));
            status.add(Restrictions.eq("requestState", 35));
            status.add(Restrictions.eq("requestState", 36));
            status.add(Restrictions.eq("requestState", 37));

            crit.add(Restrictions.eq("requesterId", requesterId));
            crit.add(status);

            returnedRequests = crit.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returnedRequests;
    }

    //Any user that is a benco gets these requests
    @Override
    public List<Request> getAttnRequestsBenCo() {
        Session session = HibernateUtil.getSession();
        List<Request> returnedRequests = null;

        try{

            Criteria crit = session.createCriteria(Request.class);
            //-Get all user requests WHERE status = 30-37, 41
            Disjunction status = Restrictions.disjunction();
            status.add(Restrictions.eq("requestState", 30));
            status.add(Restrictions.eq("requestState", 31));
            status.add(Restrictions.eq("requestState", 32));
            status.add(Restrictions.eq("requestState", 33));
            status.add(Restrictions.eq("requestState", 34));
            status.add(Restrictions.eq("requestState", 35));
            status.add(Restrictions.eq("requestState", 36));
            status.add(Restrictions.eq("requestState", 37));
            status.add(Restrictions.eq("requestState", 41));
            status.add(Restrictions.eq("requestState", 42));

            crit.add(status);

            returnedRequests = crit.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returnedRequests;
    }
}
