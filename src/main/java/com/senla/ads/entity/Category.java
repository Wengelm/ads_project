package com.senla.ads.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_category")
@NoArgsConstructor
@Data
public class Category extends AbstractIdentity {

    @OneToOne(mappedBy = "category")
    private Ad ad;
    private String name;
}
