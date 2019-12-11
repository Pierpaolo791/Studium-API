package com.dmiinsider.studium.controller;

import com.dmiinsider.studium.model.News;
import com.dmiinsider.studium.service.NewsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/news")
public class NewsController {
    
    @Autowired
    private NewsService serviceNews; 
    
    @Value("${auth.token}")
    private String token;
    
    @PostMapping("add")
    @ResponseBody
    public ResponseEntity<News> addNews(@RequestBody News news, @RequestHeader("Authorization") String usrToken) {
        if(unauthorized(usrToken)) return new ResponseEntity<>(news, HttpStatus.UNAUTHORIZED);
        serviceNews.addNews(news);
        System.out.println("Una news è stata aggiunta");
        return new ResponseEntity<>(news, HttpStatus.OK);
    }
    
    @RequestMapping("check")
    public @ResponseBody ResponseEntity<List<News>> getNews(@RequestHeader("Authorization") String usrToken) {
        if(unauthorized(usrToken)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(serviceNews.checkNews(),HttpStatus.OK);
    }
    
    public boolean unauthorized(String userToken) {
        return !(this.token.equals(userToken));
    }
}
