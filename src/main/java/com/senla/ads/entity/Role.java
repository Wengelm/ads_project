package com.senla.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "t_roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractIdentity{

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleType roles;


}
