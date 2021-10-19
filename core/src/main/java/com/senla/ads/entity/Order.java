package com.senla.ads.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_oder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order extends AbstractIdentity {

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDate createdOrderDate;
    @Column(name = "product_id")
    private Long productId;
    @Column(name = "ad_id")
    private Long adId;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private OrderDetails orderDetails;
}
