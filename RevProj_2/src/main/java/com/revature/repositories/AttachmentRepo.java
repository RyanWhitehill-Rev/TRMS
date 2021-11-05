package com.revature.repositories;

import com.revature.models.Attachment;
import com.revature.models.Request;

import java.util.List;

public interface AttachmentRepo {

    public Attachment addAttachment(Attachment attachmentIn);
    public Attachment getAttachment(int id);
    public List<Attachment> getAllAttachment();
    public Attachment updateAttachment(Attachment attachmentIn);
    public Attachment deleteAttachment(int id);

    public List<Attachment> getAllAttachmentsOfRequest(int id);

}
