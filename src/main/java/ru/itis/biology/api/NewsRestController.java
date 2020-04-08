package ru.itis.biology.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.biology.dto.CommentDto;
import ru.itis.biology.models.Comment;
import ru.itis.biology.models.News;
import ru.itis.biology.service.CommentService;
import ru.itis.biology.service.NewsService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/news")
public class NewsRestController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<News>> getPosts() {
        return new ResponseEntity<>(newsService.findAllNews(), HttpStatus.OK);
    }

    @GetMapping("/{news-id}")
    public ResponseEntity<Map<News, List<Comment>>> getConcreteNewsPage(@PathVariable("news-id") Long newsId) {
        News news = newsService.findByID(newsId).get();
        List<Comment> commentList = commentService.getComments(newsId);
        Map<News, List<Comment>> map = new HashMap<>();
        map.put(news, commentList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment/{news-id}")
    public ResponseEntity<?> setCommentNewsPage(@RequestParam(value = "text") String text, @PathVariable("news-id") Long newsId) {
        CommentDto commentDto = CommentDto.builder()
                .text(text)
                .newsId(newsId)
                .build();
        commentService.send(newsId, commentDto);
        return ResponseEntity.accepted().build();
    }


}
