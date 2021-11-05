package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.EventCategory;
import com.revature.models.GradeType;
import com.revature.services.EventCategoryServiceImpl;
import com.revature.services.GradeTypeServiceImpl;
import com.revature.util.LogUtil;
import io.javalin.http.Handler;

import java.util.List;

public class GradeTypeController {



    Gson gson = new Gson();
    GradeTypeServiceImpl gts;

    public GradeTypeController(GradeTypeServiceImpl gts) {
        this.gts = gts;
    }


    public Handler getAllGradeTypes = (ctx) -> {

        List<GradeType> returnedGCs = gts.getGradeTypes();
        if(returnedGCs == null) {
            ctx.result("Something went wrong retrieving grade types - check DB setup!");
            ctx.status(500);
        }

        ctx.result(gson.toJson(returnedGCs));
        ctx.status(200);
    };


    public Handler getGradeTypeById = (ctx) -> {

        String requestedGradeTypeIdString = ctx.pathParam("gt_id");
        int requestedGradeTypeId = -1;

        try {
            requestedGradeTypeId = Integer.parseInt(requestedGradeTypeIdString);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }
        if(requestedGradeTypeId == -1) {
            LogUtil.logger.error("Bad grade type ID");
            ctx.result("Bad grade type ID");
            ctx.status(400);
        }

        GradeType returnedGT = gts.getGradeTypeById(requestedGradeTypeId);
        if(returnedGT == null) {
            LogUtil.logger.warn("Could not retrieve grade type");
            ctx.result("Could not retrieve grade type");
            ctx.status(400);
        }

        ctx.result(gson.toJson(returnedGT));
        ctx.status(200);
    };
}
