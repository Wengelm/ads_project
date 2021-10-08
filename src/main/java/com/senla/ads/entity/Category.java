package com.senla.ads.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_category")
@NoArgsConstructor
@Getter
@Setter
public class Category extends AbstractIdentity {

    @OneToOne(mappedBy = "category")
    private Ad ad;
    private String name;
}
