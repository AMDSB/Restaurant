package dev.sn.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@RequiredArgsConstructor
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private Date date;

    @NotNull
    private Double montantTotal;

//    private Utilisateur utilisateur;
//
//    private Commande commande;
}
