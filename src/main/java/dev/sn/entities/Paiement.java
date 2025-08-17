package dev.sn.entities;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;


}
