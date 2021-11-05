package com.revature.repositories;

import com.revature.models.Request;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import com.revature.util.LogUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class UserRepoHbImpl implements UserRepo{

    /*
    @Override
    public User addUser(User userIn) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            userIn.setId((int)session.save(userIn));

            session.getTransaction().commit();
        } catch (HibernateException e) {
            LogUtil.logger.error(e);
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return userIn;
    }
    */


    @Override
    public User getUser(int id) {

        Session session = HibernateUtil.getSession();

        User returnedUser = null;

        try {

            returnedUser = session.get(User.class, id);

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return returnedUser;
    }

    /*
    @Override
    public List<User> getAllUsers() {

        Session session = HibernateUtil.getSession();
        List<User> userList = new ArrayList<>();

        try{

            userList = session.createQuery("FROM User").list();

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return userList;
    }

    @Override
    public User updateUser(User userIn) {
        Session session = HibernateUtil.getSession();

        try{
            session.beginTransaction();

            session.update(userIn);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }

        return userIn;
    }

    @Override
    public User deleteUser(int id) {
        Session session = HibernateUtil.getSession();

        User deletedUser;

        try{

            session.beginTransaction();

            deletedUser = session.get(User.class, id);

            session.delete(deletedUser);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }

        return deletedUser;
    }
     */


    @Override
    public User getUserByUsername(String usernameIn) {

        Session session = HibernateUtil.getSession();
        User returnedUser = null;

        try{
            //Deprecated, but works for now
            Criteria crit = session.createCriteria(User.class);
            //Define the criteria: We want the name column to equal the name we provided
            crit.add(Restrictions.eq("username", usernameIn));

            //Refactor? Probably not needed
            if(crit.list().size() > 0) {
                returnedUser = (User) crit.list().get(0);
            }

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return returnedUser;
    }

    @Override
    public List<User> getUsersBySupervisor(int id) {
        Session session = HibernateUtil.getSession();
        List<User> returnedUsers = null;

        try{

            Criteria crit = session.createCriteria(User.class);
            crit.add(Restrictions.eq("supervisorId", id));

            returnedUsers = crit.list();

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return returnedUsers;
    }

    @Override
    public List<User> getUsersByDepartment(int id) {
        Session session = HibernateUtil.getSession();
        List<User> returnedUsers = null;

        try{

            Criteria crit = session.createCriteria(User.class);
            crit.add(Restrictions.eq("departmentId", id));

            returnedUsers = crit.list();

        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return returnedUsers;
    }
}
