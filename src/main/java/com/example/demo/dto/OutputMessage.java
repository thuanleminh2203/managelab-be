package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OutputMessage extends MessageDTO {

    private String time;

    public OutputMessage(final String from, final String text, final String time) {
        setFrom(from);
        setText(text);
        this.time = time;
    }

}
