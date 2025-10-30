package dev.sn.entities;

import dev.sn.entities.enums.CommandeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@RequiredArgsConstructor
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private int numero;

    @Column(nullable = false, unique = true)
    private String numeroCommande;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;


    @ManyToOne
    @JoinColumn(name = "serveur_id" , nullable = true)
    private Utilisateur serveur;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LigneCommande> lignes = new ArrayList<>();

    @Column(name = "date_commande", nullable = false)
    private LocalDateTime dateCommande;

    @Column(precision = 10, scale = 2)
    private BigDecimal sousTotal;

    @Column(precision = 5, scale = 2)
    private BigDecimal tauxTaxe = BigDecimal.ZERO;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTaxe;

    @Column(precision = 10, scale = 2)
    private BigDecimal montantTotal;

    @Column(length = 500)
    private String notes;

    @OneToOne(mappedBy = "commande")
    private Facture facture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommandeStatus status = CommandeStatus.EN_COURS;


    @PrePersist
    protected void onCreate() {
        if (dateCommande == null) {
            dateCommande = LocalDateTime.now();
        }
        if (numeroCommande == null) {
            numeroCommande = "CMD-" + System.currentTimeMillis();
        }
    }


    public void calculerMontants() {
        sousTotal = lignes.stream()
                .map(LigneCommande::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal taxe = sousTotal
                .multiply(tauxTaxe != null ? tauxTaxe : BigDecimal.ZERO)
                .divide(BigDecimal.valueOf(100));

        montantTaxe = taxe;
        montantTotal = sousTotal.add(taxe);
    }




}
