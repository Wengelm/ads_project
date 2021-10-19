package com.senla.ads.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "t_ad_payment_status")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdPaymentStatus extends AbstractIdentity {

    private boolean isPaid;
    private LocalDate untilPaied;
    @JsonIgnore
    @OneToOne
    private Ad ad;
}