package com.senla.ads.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails extends AbstractIdentity {

    @ManyToOne
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    private Order Order;
    @OneToOne(cascade = CascadeType.ALL)
    private Product product;
    @OneToOne(cascade = CascadeType.ALL)
    private Ad ad;
}
