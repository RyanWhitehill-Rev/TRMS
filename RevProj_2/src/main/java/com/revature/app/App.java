package com.revature.app;

import com.revature.controllers.*;
import com.revature.models.Event;
import com.revature.repositories.*;
import com.revature.services.*;
import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {

        //Establish the javalin object
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
                });



        //Setup routes and layers
        establishRoutes(app);

        //Run javalin
        app.start();

    }

    public static void establishRoutes(Javalin app) {

        UserRepoHbImpl ur = new UserRepoHbImpl();
        RequestRepoHbImpl rr = new RequestRepoHbImpl();
        DepartmentRepoHbImpl dr = new DepartmentRepoHbImpl();
        EventRepoHbImpl er = new EventRepoHbImpl();
        EventCategoryRepoHbImpl ecr = new EventCategoryRepoHbImpl();
        GradeTypeRepoHbImpl gtr = new GradeTypeRepoHbImpl();
        RequestStateRepoHbImpl rsr = new RequestStateRepoHbImpl();
        AttachmentRepoHbImpl ar = new AttachmentRepoHbImpl();

        UserServiceImpl us = new UserServiceImpl(ur);
        RequestServiceImpl rs = new RequestServiceImpl(rr);
        DepartmentServiceImpl ds = new DepartmentServiceImpl(dr);
        EventServiceImpl es = new EventServiceImpl(er);
        EventCategoryServiceImpl ecs = new EventCategoryServiceImpl(ecr);
        GradeTypeServiceImpl gts = new GradeTypeServiceImpl(gtr);
        RequestStateServiceImpl rss = new RequestStateServiceImpl(rsr);
        AttachmentServiceImpl as = new AttachmentServiceImpl(ar);

        UserController uc = new UserController(us);
        EventController ec = new EventController(es);
        EventCategoryController ecc = new EventCategoryController(ecs);
        GradeTypeController gtc = new GradeTypeController(gts);
        RequestStateController rsc = new RequestStateController(rss);
        AttachmentController ac = new AttachmentController(as);

        RequestController rc = new RequestController(rs, us, ds, es, as);

        app.post("/login", uc.logInAttempt);

        app.post("/requests", rc.addRequest);

        //Probably going to have to alter this method!
        app.patch("/requests", rc.updateRequestStatusCode);

        app.patch("/requests/add_attachment", rc.addAttachmentAlterStatus);

        app.get("/requests/:r_id", rc.getRequest);
        app.get("/requests/user/rmb/:u_id", rc.getRemainingReimbursementOfUser);
        app.get("/requests/user/:u_id", rc.getAllRequestsByRequesterId);


        app.get("/event_categories", ecc.getAllEventCategories);
        app.get("/event_categories/:ec_id", ecc.getEventCategoryById);

        app.get("/grade_types", gtc.getAllGradeTypes);
        app.get("/grade_types/:gt_id", gtc.getGradeTypeById);

        app.get("/events/:e_id", ec.getEventById);

        app.get("/request_states/:rs_id", rsc.getRequestStateById);

        app.get("/attachments/requests/:r_id", ac.getAllAttachmentsOfRequest);


    }


}
