package dev.sn.mappers;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurDto toUtilsateurDto(Utilisateur utilisateur);
    Utilisateur toUtilisateur(UtilisateurDto utilisateurDto);

}
