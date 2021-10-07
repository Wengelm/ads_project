package com.senla.ads.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "t_ad")
public class Ad extends AbstractIdentity{

    private String title;
    private String description;
    private LocalDate createdDate;
    @Enumerated(EnumType.STRING)
    private AdStatus status;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Comment> comments = new ArrayList<>();

}
