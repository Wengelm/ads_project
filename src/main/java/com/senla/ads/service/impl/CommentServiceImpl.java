package com.senla.ads.service.impl;

import com.senla.ads.entity.Comment;
import com.senla.ads.repository.CommentRepository;
import com.senla.ads.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {

        Comment c = commentRepository.save(comment);
        System.out.println(c.getText());

        return c;
    }

    @Override
    public Comment update(Comment comment) {
        return null;
    }

    @Override
    public Comment getCategoryById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
