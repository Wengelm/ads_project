package com.senla.ads.service;

import com.senla.ads.entity.Review;
import com.senla.ads.entity.User;
import com.senla.ads.repository.ReviewRepository;
import com.senla.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userServiceRating")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepositoryRating")
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    @Override
    public void changeUserRating(User user) {

        List<Review> reviews = reviewRepository.getByReviewerUser(user.getId());

        double countUserGrade = reviews.stream().mapToInt(Review::getGrade).sum();
        double tmpUserRating = countUserGrade / reviews.size();
        user.setRating(tmpUserRating);
        userRepository.save(user);

    }
}
