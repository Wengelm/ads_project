package com.senla.ads.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "t_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SecondaryTable(name = "t_rating", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends AbstractIdentity  implements Serializable {

    @Serial
    private static final long serialVersionUID = 2330L;

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
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Ad> ads = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderDetails> orderDetails = new HashSet<>();

}
