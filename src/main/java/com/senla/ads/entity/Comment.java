package com.senla.ads.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_comment")
public class Comment extends AbstractIdentity {

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String text;
}
