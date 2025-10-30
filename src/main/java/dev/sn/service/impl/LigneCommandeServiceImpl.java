package dev.sn.service.impl;

import dev.sn.dtos.LigneCommandeDto;
import dev.sn.entities.Commande;
import dev.sn.entities.LigneCommande;
import dev.sn.entities.Plat;
import dev.sn.mappers.LigneCommandeMapper;
import dev.sn.repositories.CommandeRepository;
import dev.sn.repositories.LigneCommandeRepository;
import dev.sn.repositories.PlatRepository;
import dev.sn.service.interfaces.LigneCommandeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class LigneCommandeServiceImpl implements LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;
    private final CommandeRepository commandeRepository;
    private final PlatRepository platRepository;
    private final LigneCommandeMapper ligneCommandeMapper;

    public LigneCommandeServiceImpl(
            LigneCommandeRepository ligneCommandeRepository,
            CommandeRepository commandeRepository,
            PlatRepository platRepository,
            LigneCommandeMapper ligneCommandeMapper
    ) {
        this.ligneCommandeRepository = ligneCommandeRepository;
        this.commandeRepository = commandeRepository;
        this.platRepository = platRepository;
        this.ligneCommandeMapper = ligneCommandeMapper;
    }

    private void updateMontants(Commande commande) {
        BigDecimal sousTotal = commande.getLignes().stream()
                .map(LigneCommande::getSousTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        commande.setSousTotal(sousTotal);

        BigDecimal tauxTaxe = commande.getTauxTaxe() != null
                ? commande.getTauxTaxe()
                : BigDecimal.ZERO;

        BigDecimal taxe = sousTotal
                .multiply(tauxTaxe)
                .divide(BigDecimal.valueOf(100));

        commande.setMontantTaxe(taxe);

        commande.setMontantTotal(sousTotal.add(taxe));

        commandeRepository.save(commande);
    }

    @Override
    public LigneCommandeDto addPlatToCommande(Long commandeId, Long platId, int quantite) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        Plat plat = platRepository.findById(platId)
                .orElseThrow(() -> new RuntimeException("Plat non trouvé"));



        LigneCommande ligne = new LigneCommande();
        ligne.setCommande(commande);
        ligne.setPlat(plat);
        ligne.setQuantite(quantite);
        ligne.setPrixUnitaire(plat.getPrix());
        ligne.setSousTotal(plat.getPrix().multiply(BigDecimal.valueOf(quantite)));

        LigneCommande saved = ligneCommandeRepository.save(ligne);

        commande.getLignes().add(saved);
        updateMontants(commande);

        return ligneCommandeMapper.toDto(saved);
    }

    @Override
    public LigneCommandeDto updateQuantite(Long ligneId, int nouvelleQuantite) {
        LigneCommande ligne = ligneCommandeRepository.findById(ligneId)
                .orElseThrow(() -> new RuntimeException("Ligne commande non trouvée"));

        ligne.setQuantite(nouvelleQuantite);
        ligne.setSousTotal(ligne.getPlat().getPrix()
                .multiply(BigDecimal.valueOf(nouvelleQuantite)));

        LigneCommande updated = ligneCommandeRepository.save(ligne);

        updateMontants(updated.getCommande());

        return ligneCommandeMapper.toDto(updated);
    }

    @Override
    public void removeLigne(Long ligneId) {
        LigneCommande ligne = ligneCommandeRepository.findById(ligneId)
                .orElseThrow(() -> new RuntimeException("Ligne commande inexistante"));

        Commande commande = ligne.getCommande();

        ligneCommandeRepository.delete(ligne);

        commande.getLignes().remove(ligne);
        updateMontants(commande);
    }

    @Override
    public List<LigneCommandeDto> findByCommandeId(Long commandeId) {
        return ligneCommandeRepository.findByCommandeId(commandeId)
                .stream()
                .map(ligneCommandeMapper::toDto)
                .toList();
    }

    @Override
    public List<LigneCommandeDto> findAll() {
        return ligneCommandeRepository.findAll()
                .stream()
                .map(ligneCommandeMapper::toDto)
                .toList();
    }

}
