package com.revature.repositories;

import com.revature.models.Department;
import com.revature.util.HibernateUtil;
import com.revature.util.LogUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DepartmentRepoHbImpl implements DepartmentRepo{



    @Override
    public List<Department> checkIfDeptHead(int userId){
        List<Department> returnedList = null;
        Session session = HibernateUtil.getSession();

        try {

            Criteria crit = session.createCriteria(Department.class);
            crit.add(Restrictions.eq("deptHeadId", userId));

            returnedList = crit.list();


        } catch (HibernateException e) {
            LogUtil.logger.error(e);
        } finally {
            session.close();
        }

        return returnedList;
    }
}
