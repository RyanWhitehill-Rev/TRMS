package com.revature.services;

import com.revature.models.GradeType;

import java.util.List;

public interface GradeTypeService {

    public List<GradeType> getGradeTypes();
    public GradeType getGradeTypeById(int id);

}
