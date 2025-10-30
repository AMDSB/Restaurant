package dev.sn.entities;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@RequiredArgsConstructor
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "factures")
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private Date date;

//    @NotNull
//    private Double montantTotal;

    @Column(nullable = false, unique = true)
    private String numeroFacture;

    @OneToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private Commande commande;

    @Column(name = "date_emission", nullable = false)
    private LocalDateTime dateEmission;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal montantTotal;

//    private Utilisateur utilisateur;
//
//    private Commande commande;
}
