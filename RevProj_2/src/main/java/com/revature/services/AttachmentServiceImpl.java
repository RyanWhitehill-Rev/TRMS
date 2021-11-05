package com.revature.services;

import com.revature.models.Attachment;
import com.revature.repositories.AttachmentRepoHbImpl;

import java.util.List;

public class AttachmentServiceImpl implements AttachmentService{

    AttachmentRepoHbImpl ar = new AttachmentRepoHbImpl();

    public AttachmentServiceImpl(AttachmentRepoHbImpl ar) {
        this.ar = ar;
    }

    @Override
    public Attachment addAttachment(Attachment attachmentIn) {
        return ar.addAttachment(attachmentIn);
    }

    @Override
    public Attachment getAttachment(int id) {
        return ar.getAttachment(id);
    }

    @Override
    public List<Attachment> getAllAttachment() {
        return ar.getAllAttachment();
    }

    @Override
    public Attachment updateAttachment(Attachment attachmentIn) {
        return ar.updateAttachment(attachmentIn);
    }

    @Override
    public Attachment deleteAttachment(int id) {
        return ar.deleteAttachment(id);
    }

    @Override
    public List<Attachment> getAllAttachmentsOfRequest(int id) {
        return ar.getAllAttachmentsOfRequest(id);
    }
}
