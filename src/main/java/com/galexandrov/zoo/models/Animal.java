package com.galexandrov.zoo.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Animal extends BaseEntity{

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private AnimalType animalType;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
