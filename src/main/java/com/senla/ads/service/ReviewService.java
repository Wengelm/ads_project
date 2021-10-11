package com.senla.ads.service;

import com.senla.ads.entity.Comment;
import com.senla.ads.entity.Review;
import com.senla.ads.entity.User;

import java.util.List;

public interface ReviewService {

    Review save(Review r);

    Review update(Review r);

    Review getReviewById(Long id);

    List<Review> getReviewsByUser(User user);

    void delete(Long id);

    List<Review> findAll();
}
