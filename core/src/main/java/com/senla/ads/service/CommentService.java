package com.senla.ads.service;

import com.senla.ads.entity.Ad;
import com.senla.ads.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    Comment update(Comment comment);

    Comment getCommentById(Long id);

    List<Ad> getCommentByAd(Ad ad);

    void delete(Long id);

    List<Comment> findAll();


}
