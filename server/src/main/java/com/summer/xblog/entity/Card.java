package com.summer.xblog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    @Column(length = 20)
    private String name;
    @NonNull
    @Column(length = 10)
    private String category;
    @Column(length = 300)
    private String desc;
    private long view;
    private long star;
    @Transient
    private boolean starred;
    public Card erase() {
        view = 0;
        star = 0;
        return this;
    }
}
