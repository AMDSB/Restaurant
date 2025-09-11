package dev.sn.entities;

import dev.sn.entities.enums.StatutCommande;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private Date date;

    private int montantTotal;

    private StatutCommande statutCommande;

    @ManyToOne
    private Facture facture;

    @ManyToOne
    private Utilisateur serveur;

    @ManyToOne
    private Table table;

}
