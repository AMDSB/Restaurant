package dev.sn.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilisateurDto {
    private Long id;

    private String nom;
    private String prenom;
    private String email;
}
