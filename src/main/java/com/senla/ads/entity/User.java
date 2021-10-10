package com.senla.ads.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "t_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SecondaryTable(name = "t_rating", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class User extends AbstractIdentity {
    @Column(unique = true)
    private String login;
    private String password;
    @Column(unique = true)
    private String email;
    private String name;
    private String surname;
    private String age;
    @Column(name = "rating", table = "t_rating")
    private double rating;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ad> ads = new HashSet<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();
    @OneToMany(mappedBy = "onUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviewsOnUser = new HashSet<>();
    @OneToMany(mappedBy = "byUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviewsByUser = new HashSet<>();

}
