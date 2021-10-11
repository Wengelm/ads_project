package com.senla.ads.controller;

import com.senla.ads.dto.CategoryDto;
import com.senla.ads.dto.ReviewDto;
import com.senla.ads.dto.UserDto;
import com.senla.ads.entity.Review;
import com.senla.ads.entity.User;
import com.senla.ads.repository.ReviewRepository;
import com.senla.ads.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
public class ReviewRestController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ReviewDto addReview(@Valid @RequestBody ReviewDto reviewDto){
        Review review = modelMapper.map(reviewDto,Review.class);
        review.setByUser(userService.getUserById(20L));
        review.setOnUser(userService.getUserById(1L));
        reviewRepository.save(review);
        return  modelMapper.map(review,ReviewDto.class);
    }

    @GetMapping("/getbyuser")
    public List<ReviewDto> getReviewsByUser(@Valid @RequestBody UserDto userDto){
       User user = modelMapper.map(userDto, User.class);
        return reviewRepository.getByReviewerUser(user)
                .stream()
                .map(entity -> modelMapper.map(entity, ReviewDto.class))
                .collect(Collectors.toList());
    };
}
