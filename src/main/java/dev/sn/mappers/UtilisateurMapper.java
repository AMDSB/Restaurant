package dev.sn.mappers;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurDto toUtilisateurDto(Utilisateur utilisateur);

    Utilisateur toUtilisateur(UtilisateurDto utilisateurDto) ;



}
