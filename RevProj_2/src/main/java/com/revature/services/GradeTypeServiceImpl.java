package com.revature.services;

import com.revature.models.GradeType;
import com.revature.repositories.GradeTypeRepoHbImpl;

import java.util.List;

public class GradeTypeServiceImpl implements GradeTypeService{

    GradeTypeRepoHbImpl gtr;

    public GradeTypeServiceImpl(GradeTypeRepoHbImpl gtr) {
        this.gtr = gtr;
    }

    @Override
    public List<GradeType> getGradeTypes() {
        return gtr.getGradeTypes();
    }

    @Override
    public GradeType getGradeTypeById(int id) {
        return gtr.getGradeTypeById(id);
    }
}
