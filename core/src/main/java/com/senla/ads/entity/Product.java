package com.senla.ads.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "t_product")
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractIdentity{

    private String name;
    private Float price;
    @Column(name = "count_days")
    private Integer countDays;

}
