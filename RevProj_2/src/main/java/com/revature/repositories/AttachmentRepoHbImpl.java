package com.revature.repositories;

import com.revature.models.Attachment;
import com.revature.models.Request;
import com.revature.models.User;
import com.revature.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class AttachmentRepoHbImpl implements AttachmentRepo{


    @Override
    public Attachment addAttachment(Attachment attachmentIn) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();

            attachmentIn.setId((int)session.save(attachmentIn));

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return attachmentIn;
    }

    @Override
    public Attachment getAttachment(int id) {

        Session session = HibernateUtil.getSession();

        Attachment returnedAttachment = null;

        try {

            returnedAttachment = session.get(Attachment.class, id);

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returnedAttachment;
    }

    @Override
    public List<Attachment> getAllAttachment() {

        Session session = HibernateUtil.getSession();
        List<Attachment> attachmentList = new ArrayList<>();

        try{

            attachmentList = session.createQuery("FROM Attachment").list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return attachmentList;
    }

    @Override
    public Attachment updateAttachment(Attachment attachmentIn) {
        Session session = HibernateUtil.getSession();

        try{
            session.beginTransaction();

            session.update(attachmentIn);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }

        return attachmentIn;
    }

    @Override
    public Attachment deleteAttachment(int id) {
        Session session = HibernateUtil.getSession();

        Attachment deletedAttachment;

        try{

            session.beginTransaction();

            deletedAttachment = session.get(Attachment.class, id);

            session.delete(deletedAttachment);

            session.getTransaction().commit();

        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }

        return deletedAttachment;
    }

    @Override
    public List<Attachment> getAllAttachmentsOfRequest(int id) {
        Session session = HibernateUtil.getSession();
        List<Attachment> returnedAttachments = null;

        try{

            Criteria crit = session.createCriteria(Attachment.class);
            crit.add(Restrictions.eq("requestId", id));

            returnedAttachments = crit.list();

        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return returnedAttachments;
    }
}
