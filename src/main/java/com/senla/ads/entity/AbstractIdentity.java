package com.senla.ads.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
