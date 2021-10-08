package com.senla.ads.dto;

import com.senla.ads.entity.AdStatus;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class AdDto {
    private Long id;
    private String title;
    private String description;
    private LocalDate createdDate;
    private AdStatus status;
    private UserDto user;
    private List<CommentDto> comments = new ArrayList<>();

}
