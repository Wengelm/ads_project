package com.senla.ads.entity;

/**
 * Abstract class with  <b>id</b>.
 * @autor Pavel Zazulinski
 * @version 1.0
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractIdentity {


    /** Id for entities who extend abstract class */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
