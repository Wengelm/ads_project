package com.senla.ads.controller;


import com.senla.ads.dto.CommentDto;
import com.senla.ads.entity.Comment;
import com.senla.ads.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/comment")
@SecurityRequirement(name = "bearerAuth")
public class CommentRestController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/addComment")
    public CommentDto addComment(@Validated  @RequestBody CommentDto comment) {
        Comment newComment= modelMapper.map(comment, Comment.class);
        commentService.save(newComment);

        return modelMapper.map(newComment, CommentDto.class);
    }


}
