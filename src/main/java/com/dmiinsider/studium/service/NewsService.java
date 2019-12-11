package com.dmiinsider.studium.service;

import com.dmiinsider.studium.model.News;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class NewsService {
    
    List<News> list = new LinkedList<>(); 
    
    public void addNews(News n) {
        list.add(n);
    }
    public List<News> checkNews() {
        List<News> tmpList = list.stream().collect(Collectors.toList());
        list.clear();
        return tmpList;
    }
}
