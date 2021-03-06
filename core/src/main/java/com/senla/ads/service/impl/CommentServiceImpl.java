package com.senla.ads.service.impl;

import com.senla.ads.entity.Ad;
import com.senla.ads.entity.Comment;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.repository.CommentRepository;
import com.senla.ads.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Comment save(Comment comment) {

        Comment c = commentRepository.save(comment);
        return c;
    }

    @Override
    @PreAuthorize("authentication.principal.username.equals(#comment.user.login)")
    public Comment update(Comment comment) {

        Comment com = commentRepository.findById(comment.getId())
                .orElseThrow(() -> new MyEntityNotFoundException(comment.getId()));;
        com.setText(comment.getText());
        return com;
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }

    @Override
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Ad> getCommentByAd(Ad ad) {
        return commentRepository.getCommentByAd(ad.getId());
    }


    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
}
