package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.Attachment;
import com.revature.models.CMDJson;
import com.revature.models.Request;
import com.revature.repositories.AttachmentRepoHbImpl;
import com.revature.services.AttachmentServiceImpl;
import com.revature.services.EventCategoryServiceImpl;
import com.revature.util.LogUtil;
import io.javalin.http.Handler;

import java.util.List;

public class AttachmentController {

    Gson gson = new Gson();
    AttachmentServiceImpl as;

    public AttachmentController(AttachmentServiceImpl as) {
        this.as = as;
    }

    public Handler addAttachment = (ctx) -> {

        CMDJson cmdJson = gson.fromJson(ctx.body(), CMDJson.class);

        Attachment newAttachment = new Attachment();

        newAttachment.setRequestId(cmdJson.getRequestId());
        newAttachment.setSatisfied(cmdJson.isSatisfied());
        newAttachment.setFilepath(cmdJson.getFilepath());
        newAttachment.setAddedText(cmdJson.getText());

        if(cmdJson.getDemandUserId() != 0) {
            newAttachment.setDemanderId(cmdJson.getDemandUserId());
        }
        if(cmdJson.getSupplyUserId() != 0) {
            newAttachment.setSupplierId(cmdJson.getSupplyUserId());
        }

        newAttachment = as.addAttachment(newAttachment);

        if(newAttachment == null) {
            LogUtil.logger.error("New Attachment failed");
            ctx.result(gson.toJson(null));
            ctx.status(500);
            return;
        }

        System.out.println(gson.toJson(newAttachment));
        ctx.result(gson.toJson(newAttachment));
        ctx.status(200);
    };

    public Handler getAllAttachmentsOfRequest = (ctx) -> {

        String requestIdString = ctx.pathParam(":r_id");
        int requestId = -1;
        try {
            requestId = Integer.parseInt(requestIdString);
        } catch (Exception e) {
            LogUtil.logger.error(e);
        }
        if(requestId == -1) {
            LogUtil.logger.error("Bad request ID");
            ctx.result("Bad request ID");
            ctx.status(400);
        }

        List<Attachment> attachmentList = as.getAllAttachmentsOfRequest(requestId);

        System.out.println(gson.toJson(attachmentList));
        ctx.result(gson.toJson(attachmentList));
        ctx.status(200);

    };

}
