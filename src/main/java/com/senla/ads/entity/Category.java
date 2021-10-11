package com.senla.ads.entity;

import lombok.*;


import javax.persistence.*;


@Entity
@Table(name = "t_category")
@NoArgsConstructor
@Getter
@Setter
public class Category extends AbstractIdentity {

    private String name;

}
