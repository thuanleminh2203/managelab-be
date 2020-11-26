package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsSearchRequest implements Serializable {
    private int pageIndex;
    private int pageSize;

}
