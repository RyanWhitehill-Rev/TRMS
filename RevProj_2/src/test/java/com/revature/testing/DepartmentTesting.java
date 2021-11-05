package com.revature.testing;

import com.revature.models.Department;
import com.revature.repositories.DepartmentRepoHbImpl;
import com.revature.services.DepartmentServiceImpl;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentTesting {

    DepartmentRepoHbImpl dr = new DepartmentRepoHbImpl();
    DepartmentServiceImpl ds = new DepartmentServiceImpl(dr);

    //Repo
    @Test
    public void checkIfDeptHeadTest() {
        List<Department> expectedList = new ArrayList<>();
        List<Department> returnedList = dr.checkIfDeptHead(5);
        assertEquals(expectedList, returnedList);
        expectedList.add(new Department(1, "Dept A", 1));
        returnedList = dr.checkIfDeptHead(1);
        assertEquals(expectedList, returnedList);
    }

    //Service

    @Test
    public void checkIfDeptHeadTestService() {
        List<Integer> expectedList = new ArrayList<>();
        List<Integer> returnedList = ds.checkIfDeptHead(5);
        assertEquals(expectedList, returnedList);
        expectedList.add(1);
        returnedList = ds.checkIfDeptHead(1);
        assertEquals(expectedList, returnedList);
    }

}
