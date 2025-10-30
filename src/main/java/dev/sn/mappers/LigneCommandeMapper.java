package dev.sn.mappers;

import dev.sn.dtos.LigneCommandeDto;
import dev.sn.entities.LigneCommande;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LigneCommandeMapper {

    @Mapping(target = "commande", ignore = true)
    @Mapping(target = "plat", ignore = true)
    LigneCommande toEntity(LigneCommandeDto dto);

    @Mapping(target = "commandeId", source = "commande.id")
    @Mapping(target = "platId", source = "plat.id")
    LigneCommandeDto toDto(LigneCommande entity);
}
