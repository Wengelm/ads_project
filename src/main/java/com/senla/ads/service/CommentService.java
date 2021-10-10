package com.senla.ads.service;

import com.senla.ads.entity.Category;
import com.senla.ads.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    Comment update(Comment comment);

    Comment getCategoryById(Long id);

    void delete(Long id);

    List<Comment> findAll();


}
