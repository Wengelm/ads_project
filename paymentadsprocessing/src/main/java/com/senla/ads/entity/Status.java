package com.senla.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status extends AbstractIdentity implements Serializable {

    @Enumerated(EnumType.STRING)
    private AdStatus name;


}
