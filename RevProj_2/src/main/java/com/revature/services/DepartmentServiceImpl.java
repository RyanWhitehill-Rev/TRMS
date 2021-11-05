package com.revature.services;

import com.revature.models.Department;
import com.revature.repositories.DepartmentRepoHbImpl;

import java.util.ArrayList;
import java.util.List;

public class DepartmentServiceImpl implements DepartmentService{

    DepartmentRepoHbImpl dr;

    public DepartmentServiceImpl(DepartmentRepoHbImpl dr) {
        this.dr = dr;
    }

    @Override
    public List<Integer> checkIfDeptHead(int userId) {
        List<Department> departmentList = dr.checkIfDeptHead(userId);
        List<Integer> returnedList = new ArrayList<>();

        for(Department department: departmentList) {
            returnedList.add(department.getId());
        }

        return returnedList;
    }
}
