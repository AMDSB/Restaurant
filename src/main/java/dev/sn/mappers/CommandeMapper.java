package dev.sn.mappers;

import dev.sn.dtos.CommandeDto;
import dev.sn.entities.Commande;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

//    @Mapping(target = "tableId", source = "table.id")
//    @Mapping(target = "tableNom", source = "table.name")
//    @Mapping(target = "serveurId", source = "serveur.id")
//    @Mapping(target = "serveurNom", source = "serveur.nom")
//    @Mapping(target = "factureId", source = "facture.id")
    CommandeDto toCommandeDto(Commande commande);
    @InheritInverseConfiguration
    Commande toCommande(CommandeDto commandeDto);
}
