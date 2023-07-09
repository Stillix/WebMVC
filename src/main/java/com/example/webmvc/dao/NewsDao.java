package com.example.webmvc.dao;

import com.example.webmvc.entity.News;

import java.util.List;

public interface NewsDao {

    News getNewsById(int id);

    News getNewsByTitle(String title);

}
