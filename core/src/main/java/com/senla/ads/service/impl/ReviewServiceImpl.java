package com.senla.ads.service.impl;

import com.senla.ads.entity.Review;
import com.senla.ads.entity.User;
import com.senla.ads.exception.MyEntityNotFoundException;
import com.senla.ads.jms.MessageSender;
import com.senla.ads.repository.ReviewRepository;
import com.senla.ads.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

     @Autowired
     private ReviewRepository reviewRepository;

     @Autowired
     private MessageSender messageSender;



    @Override
    public Review save(Review r) {
        Review review = reviewRepository.save(r);
        messageSender.sendReview(review);
        return review;
    }

    @Override
    public Review update(Review r) {
        Review review = reviewRepository.findById(r.getId()).orElseThrow(() -> new MyEntityNotFoundException(r.getId()));;
        review.setText(r.getText());
        review.setGrade(r.getGrade());
        reviewRepository.save(review);
        return review;
    }

    @Override
    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Review> getReviewsByUser(User user) {
        return reviewRepository.getByReviewerUser(user.getLogin());
    }

    @Override
    public void delete(Long id) {
      reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }
}
