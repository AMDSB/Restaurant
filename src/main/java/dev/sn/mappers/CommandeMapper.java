package dev.sn.mappers;


import dev.sn.dtos.CommandeDto;
import dev.sn.entities.Commande;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    Commande toCommande(CommandeDto commandeDto);
    CommandeDto toCommandeDto(Commande commande);
}
