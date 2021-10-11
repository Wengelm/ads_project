package com.senla.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "t_review")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Review  extends AbstractIdentity{

    private String text;
    private int grade;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User onUser;
    @ManyToOne
    @JoinColumn(name = "user_reviewer_id")
    private User byUser;
}
