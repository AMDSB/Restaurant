package dev.sn.entities;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@RequiredArgsConstructor

public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

//    private String type;
//    private Date dateGeneration;

    @Column(nullable = false)
    private String type; // VENTES_JOURNALIERES, MENSUEL, ANNUEL, etc.

    @Column(nullable = false)
    private LocalDateTime dateDebut;

    @Column(nullable = false)
    private LocalDateTime dateFin;

    @Column(name = "date_generation", nullable = false)
    private LocalDateTime dateGeneration;

    @ManyToOne
    @JoinColumn(name = "genere_par")
    private Utilisateur generePar;

    @Column(precision = 15, scale = 2)
    private BigDecimal chiffreAffaires;

    @Column(precision = 15, scale = 2)
    private BigDecimal montantTaxes;

    private Integer nombreCommandes;

    private Integer nombreClients;

    @Column(length = 5000)
    private String details; // JSON ou texte avec détails additionnels

    @PrePersist
    protected void onCreate() {
        if (dateGeneration == null) {
            dateGeneration = LocalDateTime.now();
        }
    }

}
