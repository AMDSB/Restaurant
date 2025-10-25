package dev.sn.mappers;

import dev.sn.dtos.UserDto;
import dev.sn.entities.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UserDto toUtilsateurDto(Utilisateur utilisateur);
    Utilisateur toUtilisateur(UserDto utilisateurDto);

}
