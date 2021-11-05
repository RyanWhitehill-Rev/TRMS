package com.revature.repositories;

import com.revature.models.GradeType;

import java.util.List;

public interface GradeTypeRepo {

    public List<GradeType> getGradeTypes();
    public GradeType getGradeTypeById(int id);
}
