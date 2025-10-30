package dev.sn.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeDto {
    private Long id;
    private String numeroCommande;

    private Long tableId;         // pour référencer la table sans charger tout l'objet
//    private String tableNom;      // si tu veux afficher le nom ou numéro de la table
//private int numero;
//    private Long serveurId;       // id du serveur (utilisateur)
//    private String serveurNom;    // nom du serveur (facultatif)

    private LocalDateTime dateCommande;
    private BigDecimal sousTotal;
    private BigDecimal tauxTaxe;
    private BigDecimal montantTaxe;
    private BigDecimal montantTotal;
    private String notes;

    private Long factureId;

    private String status;

}
