package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNewsSearchResponse implements Serializable {
    private int totalRecords;
    private List<NewSearchDTO> listNewsSearch;
}
