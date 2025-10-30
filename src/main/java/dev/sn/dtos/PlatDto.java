package dev.sn.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PlatDto {

    private Long id;
    private String nom;
    private String description;
    private String categorie;
    private String image;
    private Boolean disponible;
    private BigDecimal prix;
}
