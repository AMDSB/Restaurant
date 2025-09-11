package dev.sn.service.impl;

import dev.sn.dtos.CommandeDto;
import dev.sn.entities.Commande;
import dev.sn.mappers.CommandeMapper;
import dev.sn.repositories.CommandeRepository;
import dev.sn.service.interfaces.CommandeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {

    private CommandeRepository commandeRepository; 
    private CommandeMapper commandeMapper;

    private CommandeDto save(CommandeDto commandeDto){
        Commande commande = commandeMapper.toCommande(commandeDto);
        Commande commandesaved = commandeRepository.save(commande);
        return commandeMapper.toCommandeDto(commandesaved);
    }
    
    @Override
    public CommandeDto create(CommandeDto commandeDto) {
        return save(commandeDto);
    }

    @Override
    public List<CommandeDto> getAll() {
        return commandeRepository.findAll().stream()
                .map(commandeMapper::toCommandeDto).
                collect(Collectors.toList());
    }

    @Override
    public Optional<CommandeDto> getById(int id) {
        return commandeRepository.findById(id)
                .map(commandeMapper::toCommandeDto);
    }

    @Override
    public CommandeDto update(int id, CommandeDto commandeDto) {
        Commande existingCommande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande avec l'id " + id + " introuvable"));

        // Mettre à jour les champs (exemple, adapte avec tes attributs)

//        if (commandeDto.getMontantTotal() != 0) {
//            existingCommande.setMontantTotal(commandeDto.getMontantTotal());
//        }
//        if (commandeDto.getStatutCommande() != null) {
//            existingCommande.setStatutCommande(commandeDto.getStatutCommande());
//        }
//        if (commandeDto.getServeur() != null) {
//            existingCommande.setServeur(commandeMapper.toCommande(commandeDto.getServeur()));
//        }
        // Sauvegarde
        Commande updatedCommande = commandeRepository.save(existingCommande);

        // Retourne le DTO
        return commandeMapper.toCommandeDto(updatedCommande);
    }

    @Override
    public void delete(int id) {
        commandeRepository.deleteById(id);
    }

    @Override
    public List<CommandeDto> searchByDate(Date date) {
        return commandeRepository.findByDate(date);
    }
}

