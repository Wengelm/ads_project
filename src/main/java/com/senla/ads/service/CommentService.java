package com.senla.ads.service;

import com.senla.ads.entity.Ad;
import com.senla.ads.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    void delete(Long id);

    List<Comment> findAll();

}
