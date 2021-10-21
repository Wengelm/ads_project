package com.senla.ads.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "t_review")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review  extends AbstractIdentity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2622465842034658581L;

    private String text;
    private int grade;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User onUser;
    @ManyToOne
    @JoinColumn(name = "user_reviewer_id")
    private User byUser;
}
