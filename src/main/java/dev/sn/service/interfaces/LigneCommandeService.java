package dev.sn.service.interfaces;

import dev.sn.dtos.LigneCommandeDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface LigneCommandeService {

    LigneCommandeDto addPlatToCommande(Long commandeId, Long platId, int quantite);

    LigneCommandeDto updateQuantite(Long ligneId, int nouvelleQuantite);

    void removeLigne(Long ligneId);

    List<LigneCommandeDto> findByCommandeId(Long commandeId);

    List<LigneCommandeDto> findAll();
}
