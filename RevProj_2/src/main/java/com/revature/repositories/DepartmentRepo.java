package com.revature.repositories;

import com.revature.models.Department;

import java.util.List;

public interface DepartmentRepo {

    public List<Department> checkIfDeptHead(int userId);

}
