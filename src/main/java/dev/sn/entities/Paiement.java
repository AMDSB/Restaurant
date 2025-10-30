package dev.sn.entities;

import dev.sn.entities.enums.ModePaiement;
import dev.sn.entities.enums.StatutPaiement;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

//    @Enumerated(EnumType.STRING)
//    private ModePaiement modePaiement;

    @Enumerated(EnumType.STRING)
    private StatutPaiement statut;

    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    private Facture facture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ModePaiement modePaiement;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal montant;

    @Column(name = "date_paiement", nullable = false)
    private LocalDateTime datePaiement;

    private String reference; // Pour Mobile Money, chèque, etc.

    @Column(length = 500)
    private String notes;

    @PrePersist
    protected void onCreate() {
        if (datePaiement == null) {
            datePaiement = LocalDateTime.now();
        }
    }


}
