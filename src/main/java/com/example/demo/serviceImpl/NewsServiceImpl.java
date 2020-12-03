package com.example.demo.serviceImpl;

/**
 * @author thuanlm
 * @created at 11/26/2020
 */

import com.example.demo.dto.CommentDTO;
import com.example.demo.dto.NewSearchDTO;
import com.example.demo.dto.NewsRequest;
import com.example.demo.entity.NewsEntity;
import com.example.demo.entity.NewsUserEntity;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.NewsUserRepository;
import com.example.demo.service.NewsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final ObjectMapper mapper;
    private final CommentRepository commentRepository;
    private final NewsUserRepository newsUserRepository;

    @Override
    public List<NewSearchDTO> getNews(int pageIndex, int pageSize) {
        var data = mapper.convertValue(newsRepository.getNews(pageIndex - 1, pageSize), new TypeReference<List<NewSearchDTO>>() {
        });
        data.parallelStream().forEach(k -> {
            List<CommentDTO> listCmt = mapper.convertValue(commentRepository.getAllComments(k.getNewsId()), new TypeReference<>() {
            });
            k.setComments(listCmt);
        });
        return data;
    }

    @Override
    public NewSearchDTO save(NewsRequest newsRequest) {
        return mapper.convertValue(newsRepository.save(mapper.convertValue(newsRequest, NewsEntity.class)), NewSearchDTO.class);
//        newsRepository.save(mapper.convertValue(newsRequest, NewsEntity.class));
    }

    @Override
    public void likeThePost(int idNews, int userId) {
        var data = newsRepository.findById(idNews);
        if (data.isPresent())
            newsUserRepository.save(new NewsUserEntity(0, idNews, userId));
    }
}
