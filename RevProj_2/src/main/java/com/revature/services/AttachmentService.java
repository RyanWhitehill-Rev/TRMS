package com.revature.services;

import com.revature.models.Attachment;

import java.util.List;

public interface AttachmentService {

    public Attachment addAttachment(Attachment attachmentIn);
    public Attachment getAttachment(int id);
    public List<Attachment> getAllAttachment();
    public Attachment updateAttachment(Attachment attachmentIn);
    public Attachment deleteAttachment(int id);

    public List<Attachment> getAllAttachmentsOfRequest(int id);

}
