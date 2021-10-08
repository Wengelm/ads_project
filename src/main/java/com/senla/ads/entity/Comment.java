package com.senla.ads.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


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
