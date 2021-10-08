package com.senla.ads.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractIdentity{

    @Enumerated(EnumType.STRING)
    private RoleType roles;


}
