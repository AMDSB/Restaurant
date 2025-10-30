package dev.sn.service.interfaces;

import dev.sn.dtos.CommandeDto;

import java.util.List;
import java.util.Optional;

public interface CommandeService {

    CommandeDto createCommande(CommandeDto commandeDto);
    CommandeDto updateCommande(Long id, CommandeDto commandeDto);
Optional<CommandeDto> findById(long id);
    List<CommandeDto> getAllCommandes();
    String deleteById(long id);
    // interface
    CommandeDto updateStatus(Long commandeId, String status);

}
