package com.galexandrov.zoo.models;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ticket extends BaseEntity {

    @NotNull
    private Integer validnostDni;
    @NotNull
    private String cena;
    @NotNull
    private boolean biletaImaLiGid;
    @NotNull
    private Integer mqsto;
    @NotNull
    private Bilet bilet;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;
}
