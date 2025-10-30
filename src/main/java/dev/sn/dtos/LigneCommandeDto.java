package dev.sn.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LigneCommandeDto {
    private Long id;
    private Long commandeId;
    private Long platId;
    private int quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal sousTotal;
}
