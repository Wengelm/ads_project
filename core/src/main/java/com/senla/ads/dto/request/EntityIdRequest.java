package com.senla.ads.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class EntityIdRequest {

    @NotNull
    @Min(1)
    private Long id;
}
