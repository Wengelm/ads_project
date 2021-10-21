package com.senla.ads.dto;

import com.senla.ads.entity.AbstractIdentity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
public class AdPaymentStatusDto extends AbstractIdentity {

    private boolean isPaid;
    private LocalDate untilPaied;
}