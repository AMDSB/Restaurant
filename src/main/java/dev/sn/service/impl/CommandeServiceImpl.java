package dev.sn.service.impl;

import dev.sn.dtos.CommandeDto;
import dev.sn.entities.Commande;
import dev.sn.entities.enums.CommandeStatus;
import dev.sn.mappers.CommandeMapper;
import dev.sn.repositories.CommandeRepository;
import dev.sn.service.interfaces.CommandeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;

    public CommandeServiceImpl(CommandeRepository commandeRepository,
                               CommandeMapper commandeMapper) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
    }

    private CommandeDto save(CommandeDto commandeDto) {
        Commande commande = commandeMapper.toCommande(commandeDto);
        Commande commandeSave =  commandeRepository.save(commande);
        return commandeMapper.toCommandeDto(commandeSave);
    }



    @Override
    public CommandeDto createCommande(CommandeDto dto) {
        Commande commande = commandeMapper.toCommande(dto);

        if (commande.getStatus() == null) {
            commande.setStatus(CommandeStatus.EN_COURS);
        }

        Commande saved = commandeRepository.save(commande);
        return commandeMapper.toCommandeDto(commande);
    }

//    public CommandeDto createCommande(CommandeDto commandeDto) {
//        return save(commandeDto);
//    }

//    @Override
//    public CommandeDto updateCommande(Long id,
//                                      CommandeDto commandeDto) {
//        return null;
//    }

    @Override
    public CommandeDto updateCommande(Long id, CommandeDto commandeDto) {
        // 1️⃣ Vérifier si la commande existe
        Optional<Commande> commandeOptional = commandeRepository.findById(id);

        if (commandeOptional.isEmpty()) {
            throw new RuntimeException("Commande non trouvée avec l'id : " + id);
        }

        Commande commande = commandeOptional.get();

        // 2️⃣ Mettre à jour uniquement les champs modifiables
        if (commandeDto.getNotes() != null) {
            commande.setNotes(commandeDto.getNotes());
        }

//        if (commandeDto.getSousTotal() != null) {
//            commande.setSousTotal(commandeDto.getSousTotal());
//        }

//        if (commandeDto.getTauxTaxe() != null) {
//            commande.setTauxTaxe(commandeDto.getTauxTaxe());
//        }

//        if (commandeDto.getMontantTaxe() != null) {
//            commande.setMontantTaxe(commandeDto.getMontantTaxe());
//        }

        if (commandeDto.getMontantTotal() != null) {
            commande.setMontantTotal(commandeDto.getMontantTotal());
        }

//        // ⚙️ Exemple : mise à jour du serveur si tu veux
//        if (commandeDto.getServeurId() != null) {
//            Utilisateur serveur = utilisateurRepository.findById(commandeDto.getServeurId())
//                    .orElseThrow(() -> new RuntimeException("Serveur non trouvé"));
//            commande.setServeur(serveur);
//        }

        // ⚙️ Exemple : mise à jour de la table
//        if (commandeDto.getTableId() != null) {
//            Table table = tableRepository.findById(commandeDto.getTableId())
//                    .orElseThrow(() -> new RuntimeException("Table non trouvée"));
//            commande.setTable(table);
//        }

        // 3️⃣ Sauvegarder la commande mise à jour
        Commande saved = commandeRepository.save(commande);

        // 4️⃣ Retourner le DTO mis à jour
        return CommandeDto.builder()
                .id(saved.getId())
                .numeroCommande(saved.getNumeroCommande())
                .tableId(saved.getTable() != null ? saved.getTable().getId() : null)
//                .numero(saved.getTable() != null ? saved.getTable().getNumero() : null)
//                .serveurId(saved.getServeur() != null ? saved.getServeur().getId() : null)
//                .serveurNom(saved.getServeur() != null ? saved.getServeur().getNom() : null)
                .dateCommande(saved.getDateCommande())
//                .sousTotal(saved.getSousTotal())
//                .tauxTaxe(saved.getTauxTaxe())
//                .montantTaxe(saved.getMontantTaxe())
                .montantTotal(saved.getMontantTotal())
                .notes(saved.getNotes())
                .factureId(saved.getFacture() != null ? saved.getFacture().getId() : null)
                .build();
    }


    @Override
    public Optional<CommandeDto> findById(long id) {
        return commandeRepository.findById(id)
                .map(commande -> CommandeDto.builder()
                        .id(commande.getId())
                        .numeroCommande(commande.getNumeroCommande())
                        .tableId(commande.getTable() != null ? commande.getTable().getId() : null)
//                        .numero(commande.getTable() != null ? commande.getTable().getNumero() : null)
//                        .serveurId(commande.getServeur() != null ? commande.getServeur().getId() : null)
//                        .serveurNom(commande.getServeur() != null ? commande.getServeur().getNom() : null)
                        .dateCommande(commande.getDateCommande())
//                        .sousTotal(commande.getSousTotal())
//                        .tauxTaxe(commande.getTauxTaxe())
//                        .montantTaxe(commande.getMontantTaxe())
                        .montantTotal(commande.getMontantTotal())
                        .notes(commande.getNotes())
                        .factureId(commande.getFacture() != null ? commande.getFacture().getId() : null)
                        .build());
    }


//    @Override
//    public Optional<CommandeDto> findById(long id) {
//        return commandeRepository.findById(id)
//                .map(commande -> CommandeDto.builder()
//                        .id(commande.getId())
//                        .numeroCommande(commande.getNumeroCommande())
//                        .tableId(commande.getTable() != null ? commande.getTable().getId() : null)
//
////                        .tableNom(commande.getTable() != null ? commande.getTable().getName() : null)
//                        .serveurId(commande.getServeur() != null ? commande.getServeur().getId() : null)
//                        .serveurNom(commande.getServeur() != null ? commande.getServeur().getNom() : null)
//                        .dateCommande(commande.getDateCommande())
//                        .sousTotal(commande.getSousTotal())
//                        .tauxTaxe(commande.getTauxTaxe())
//                        .montantTaxe(commande.getMontantTaxe())
//                        .montantTotal(commande.getMontantTotal())
//                        .notes(commande.getNotes())
//                        .factureId(commande.getFacture() != null ? commande.getFacture().getId() : null)
//                        .build());
//    }



//    @Override
//    public CommandeDto getCommandeById(Long id) {
//        return null;
//    }


    @Override
    public List<CommandeDto> getAllCommandes() {
        return commandeRepository.findAll().stream()
                .map(commandeMapper::toCommandeDto)
                .collect(Collectors.toList());
    }

    @Override
    public String deleteById(long id) {
        if (!commandeRepository.existsById(id)){
            throw new RuntimeException("Commande non trouvé avec id : " + id);
        }
        commandeRepository.deleteById(id);
        return "La commande a été supprimé avec succès !";
    }

    @Override
    public CommandeDto updateStatus(Long commandeId, String status) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

//        try {
//            commande.setStatus(CommandeStatus.valueOf(status.toUpperCase()));
//        }catch (IllegalArgumentException e){
//            throw new RuntimeException("Statut invalide : " + status);
//        }

        try {
            commande.setStatus(CommandeStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Statut invalide : " + status + ". Statuts valides : " + Arrays.toString(CommandeStatus.values()));
        }

        commandeRepository.save(commande);
        return commandeMapper.toCommandeDto(commande);
    }


}
