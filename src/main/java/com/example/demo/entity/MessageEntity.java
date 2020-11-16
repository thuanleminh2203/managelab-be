package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "message")
public class MessageEntity {
    private int messageId;
    private Integer idSend;
    private Integer idReceive;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date time;
    private boolean isRead;

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "id_send")
    public Integer getIdSend() {
        return idSend;
    }

    public void setIdSend(Integer idSend) {
        this.idSend = idSend;
    }

    @Basic
    @Column(name = "id_receive")
    public Integer getIdReceive() {
        return idReceive;
    }

    public void setIdReceive(Integer idReceive) {
        this.idReceive = idReceive;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "time")
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Basic
    @Column(name = "is_read")
    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }


}
