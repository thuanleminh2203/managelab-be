package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponseDTO implements Serializable {
    private int messageId;
    private Integer idSend;
    private Integer idReceive;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date time;
    private boolean isRead;
}
