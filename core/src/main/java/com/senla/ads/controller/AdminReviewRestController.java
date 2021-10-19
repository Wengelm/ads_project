package com.senla.ads.controller;


import com.senla.ads.dto.ProductDto;
import com.senla.ads.dto.ReviewDto;
import com.senla.ads.dto.request.EntityIdRequest;
import com.senla.ads.entity.Review;
import com.senla.ads.service.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/review")
@SecurityRequirement(name = "bearerAuth")
public class AdminReviewRestController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/all")
    public List<ReviewDto> findAll() {
        return reviewService.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, ReviewDto.class))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/delete")
    public String delete(@RequestBody EntityIdRequest entityIdRequest) {
        reviewService.delete(entityIdRequest.getId());
        return "successfully deleted";
    }

    @GetMapping("/getbyid")
    public ReviewDto getById(@RequestBody EntityIdRequest entityIdRequest) {
        Review review = reviewService.getReviewById(entityIdRequest.getId());
        return modelMapper.map(review, ReviewDto.class);
    }
}
