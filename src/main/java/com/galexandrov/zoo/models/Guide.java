package com.galexandrov.zoo.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Guide extends BaseEntity{

    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private Specialized specialized;

    @Enumerated(EnumType.STRING)
    @Column
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column
    private Gender gender;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
