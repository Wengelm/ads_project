package com.senla.ads.controller;

import com.senla.ads.dto.ReviewDto;
import com.senla.ads.dto.UserDto;
import com.senla.ads.entity.Review;
import com.senla.ads.entity.User;
import com.senla.ads.service.ReviewService;
import com.senla.ads.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
@SecurityRequirement(name = "bearerAuth")
@Slf4j
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/add")
    public ReviewDto addReview(@Valid @RequestBody ReviewDto reviewDto) {
        log.info("Request add new review");
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            Review review = modelMapper.map(reviewDto, Review.class);
            review.setByUser(userService.getUserByLogin(userDetails.getUsername()));
            review.setOnUser(userService.getUserByLogin(reviewDto.getOnUser().getLogin()));
            reviewService.save(review);
            return modelMapper.map(review, ReviewDto.class);
        } catch (SecurityException e) {
            log.warn(e.getMessage());

        }
        return null;

    }

    @GetMapping("/getbyuser")
    public List<ReviewDto> getReviewsByUser(@Valid @RequestBody UserDto userDto) {
        log.info("Get list reviews");
        User user = modelMapper.map(userDto, User.class);
        return reviewService.getReviewsByUser(user)
                .stream()
                .map(entity -> modelMapper.map(entity, ReviewDto.class))
                .collect(Collectors.toList());
    }


}
