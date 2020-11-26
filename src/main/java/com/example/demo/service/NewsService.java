package com.example.demo.service;

import com.example.demo.dto.NewSearchDTO;
import com.example.demo.dto.NewsRequest;

import java.util.List;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */
public interface NewsService {

    List<NewSearchDTO> getNews(int pageIndex, int pageSize);

    void save(NewsRequest newsRequest);

}
