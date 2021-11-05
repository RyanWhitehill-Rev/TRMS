package com.revature.testing;

import com.revature.models.GradeType;
import com.revature.repositories.GradeTypeRepoHbImpl;
import com.revature.services.GradeTypeServiceImpl;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradeTypeTesting {

    GradeTypeRepoHbImpl gtr = new GradeTypeRepoHbImpl();
    GradeTypeServiceImpl gts = new GradeTypeServiceImpl(gtr);

    //Repo

    @Test
    public void getGradeTypesTest() {
        List<GradeType> expectedGradeTypes = new ArrayList<>();
        expectedGradeTypes.add(new GradeType(1, "Alphabetical Grade", "A", true));
        expectedGradeTypes.add(new GradeType(2, "Pass/Fail", "Pass", false));
        expectedGradeTypes.add(new GradeType(3, "No grade requirement", "NA", true));
        List<GradeType> returnedGradeTypes = gtr.getGradeTypes();
        assertEquals(expectedGradeTypes, returnedGradeTypes);
    }

    @Test
    public void getGradeTypesByIdTest() {
        GradeType expectedGradeType = new GradeType(1, "Alphabetical Grade", "A", true);
        GradeType returnedGradeType = gtr.getGradeTypeById(1);
        assertEquals(expectedGradeType, returnedGradeType);
    }



    //Service

    @Test
    public void getGradeTypesTestService() {
        List<GradeType> expectedGradeTypes = new ArrayList<>();
        expectedGradeTypes.add(new GradeType(1, "Alphabetical Grade", "A", true));
        expectedGradeTypes.add(new GradeType(2, "Pass/Fail", "Pass", false));
        expectedGradeTypes.add(new GradeType(3, "No grade requirement", "NA", true));
        List<GradeType> returnedGradeTypes = gts.getGradeTypes();
        assertEquals(expectedGradeTypes, returnedGradeTypes);
    }

    @Test
    public void getGradeTypesByIdTestService() {
        GradeType expectedGradeType = new GradeType(1, "Alphabetical Grade", "A", true);
        GradeType returnedGradeType = gts.getGradeTypeById(1);
    }


}
