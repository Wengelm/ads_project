package com.senla.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status extends AbstractIdentity {

    @Enumerated(EnumType.STRING)
    private AdStatus name;
    @OneToOne(mappedBy = "status")
    private Ad ad;
}
