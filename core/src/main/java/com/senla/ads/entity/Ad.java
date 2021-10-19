package com.senla.ads.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "t_ad")
public class Ad extends AbstractIdentity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2330L;

    private String title;
    private String description;
    private LocalDate createdDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private Status status;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_status_id")
    private AdPaymentStatus adPaymentStatus;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Comment> comments = new ArrayList<>();

    public Ad(String title, String description, LocalDate createdDate, Status status, User user, Category category) {
        this.title = title;
        this.description = description;
        this.createdDate = LocalDate.now();
        this.status = new Status(AdStatus.NEW);
        this.user = user;
        this.category = category;
    }
}
