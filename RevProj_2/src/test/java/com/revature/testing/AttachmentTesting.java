package com.revature.testing;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.revature.models.Attachment;
import com.revature.repositories.AttachmentRepoHbImpl;
import com.revature.services.AttachmentServiceImpl;

import java.util.ArrayList;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AttachmentTesting {

    AttachmentRepoHbImpl ar = new AttachmentRepoHbImpl();
    AttachmentServiceImpl as = new AttachmentServiceImpl(ar);

    //repo testing

    @Test
    public void testGet() {
        Attachment expectedAttachment = new Attachment(1,1,-1,-1,true, "fake/filepath", "This is info added to the request");
        Attachment returnedAttachment = ar.getAttachment(1);
        assertEquals(returnedAttachment, returnedAttachment);
    }

    @Test
    @Order(1)
    public void testGetAll() {
        List<Attachment> attachmentList = new ArrayList<>();
        attachmentList.add(new Attachment(1,1,-1,-1,true, "fake/filepath", "This is info added to the request"));
        attachmentList.add(new Attachment(2,1,-1,-1,true, "fake/filepath", "This is also info added to the request"));

        List<Attachment> returnedAttachments = ar.getAllAttachment();
        assertEquals(returnedAttachments, attachmentList);
    }

    @Test
    @Order(3)
    public void testGetAllOfRequest() {
        List<Attachment> attachmentList = new ArrayList<>();
        attachmentList.add(new Attachment(1,1,-1,-1,true, "fake/filepath", "This is info added to the request"));
        attachmentList.add(new Attachment(2,1,-1,-1,true, "fake/filepath", "This is also info added to the request"));

        List<Attachment> returnedAttachments = ar.getAllAttachmentsOfRequest(1);
        assertEquals(returnedAttachments, attachmentList);
    }

    @Test
    @Order(5)
    public void addTest() {
        Attachment newAttachment = new Attachment(3,1,-1,-1,true, "added/filepath", "This is info added to the request");
        Attachment returnedAttachment = ar.addAttachment(newAttachment);
        assertEquals(newAttachment, returnedAttachment);
    }

    @Test
    @Order(7)
    public void updateTest() {
        Attachment updatedAttachment = new Attachment(3,1,-1,-1,true, "updated/filepath", "This is info added to the request");
        Attachment returnedAttachment = ar.updateAttachment(updatedAttachment);
        assertEquals(updatedAttachment, returnedAttachment);
    }

    @Test
    @Order(9)
    public void deleteTest() {
        Attachment deletedAttachment = new Attachment(3,1,-1,-1,true, "updated/filepath", "This is info added to the request");
        Attachment returnedAttachment = ar.deleteAttachment(3);
        assertEquals(deletedAttachment, returnedAttachment);
    }


    //Service testing


    @Test
    public void testGetService() {
        Attachment expectedAttachment = new Attachment(1,1,-1,-1,true, "fake/filepath", "This is info added to the request");
        Attachment returnedAttachment = as.getAttachment(1);
        assertEquals(returnedAttachment, returnedAttachment);
    }

    @Test
    @Order(2)
    public void testGetAllService() {
        List<Attachment> attachmentList = new ArrayList<>();
        attachmentList.add(new Attachment(1,1,-1,-1,true, "fake/filepath", "This is info added to the request"));
        attachmentList.add(new Attachment(2,1,-1,-1,true, "fake/filepath", "This is also info added to the request"));

        List<Attachment> returnedAttachments = as.getAllAttachment();
        assertEquals(returnedAttachments, attachmentList);
    }

    @Test
    @Order(4)
    public void testGetAllOfRequestService() {
        List<Attachment> attachmentList = new ArrayList<>();
        attachmentList.add(new Attachment(1,1,-1,-1,true, "fake/filepath", "This is info added to the request"));
        attachmentList.add(new Attachment(2,1,-1,-1,true, "fake/filepath", "This is also info added to the request"));

        List<Attachment> returnedAttachments = as.getAllAttachmentsOfRequest(1);
        assertEquals(returnedAttachments, attachmentList);
    }

    @Test
    @Order(6)
    public void addTestService() {
        Attachment newAttachment = new Attachment(4,1,-1,-1,true, "added/filepath", "This is info added to the request");
        Attachment returnedAttachment = as.addAttachment(newAttachment);
        assertEquals(newAttachment, returnedAttachment);
    }

    @Test
    @Order(8)
    public void updateTestService() {
        Attachment updatedAttachment = new Attachment(4,1,-1,-1,true, "updated/filepath", "This is info added to the request");
        Attachment returnedAttachment = as.updateAttachment(updatedAttachment);
        assertEquals(updatedAttachment, returnedAttachment);
    }

    @Test
    @Order(10)
    public void deleteTestService() {
        Attachment deletedAttachment = new Attachment(4,1,-1,-1,true, "updated/filepath", "This is info added to the request");
        Attachment returnedAttachment = as.deleteAttachment(4);
        assertEquals(deletedAttachment, returnedAttachment);
    }

}
