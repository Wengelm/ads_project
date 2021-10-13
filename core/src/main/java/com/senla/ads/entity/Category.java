package com.senla.ads.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "t_category")
@NoArgsConstructor
@Getter
@Setter
public class Category extends AbstractIdentity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2330L;

    private String name;

}
