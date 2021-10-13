package com.senla.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;


@Entity
@Table(name = "t_roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractIdentity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2330L;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType roles;


}
