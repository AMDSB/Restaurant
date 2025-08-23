package dev.sn.service.impl;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.Utilisateur;
import dev.sn.mappers.UtilisateurMapper;
import dev.sn.repositories.UtilisateurRepository;
import dev.sn.service.interfaces.UtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service

public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository utilisateurRepository;
    private UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    private UtilisateurDto save(UtilisateurDto utilisateurDto){
        Utilisateur utilisateur = utilisateurMapper.toUtilisateur(utilisateurDto);
        Utilisateur utilisateursaved = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toUtilisateurDto(utilisateursaved);
    }

    @Override
    public UtilisateurDto create(UtilisateurDto utilisateurDto) {
        return save(utilisateurDto);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(utilisateurMapper::toUtilisateurDto).
                collect(Collectors.toList());
    }

    @Override
    public Optional<UtilisateurDto> findById(int id) {
        return utilisateurRepository.findById(id)
                .map(utilisateurMapper::toUtilisateurDto);
    }


    @Override
    public UtilisateurDto update(int id, UtilisateurDto utilisateurDto) {
        Optional<UtilisateurDto> utilisateurDtoOptional = findById(id);

        if (utilisateurDtoOptional.isPresent()) {
            UtilisateurDto existingUtilisateur = utilisateurDtoOptional.get();

            if (utilisateurDto.getNom() != null) {
                existingUtilisateur.setNom(utilisateurDto.getNom());
            } else {
                System.out.println(" Le champ 'nom' est vide. Ancienne valeur conservée.");
            }

            if (utilisateurDto.getPrenom() != null) {
                existingUtilisateur.setPrenom(utilisateurDto.getPrenom());
            } else {
                System.out.println(" Le champ 'prenom' est vide. Ancienne valeur conservée.");
            }

            if (utilisateurDto.getEmail() != null) {
                existingUtilisateur.setEmail(utilisateurDto.getEmail());
            } else {
                System.out.println(" Le champ 'email' est vide. Ancienne valeur conservée.");
            }

            if (utilisateurDto.getPassword() != null) {
                existingUtilisateur.setPassword(utilisateurDto.getPassword());
            } else {
                System.out.println(" Le champ 'Password' est vide. Ancienne valeur conservée.");
            }

            System.out.println(" Utilisateur mis à jour avec succès !");
            return save(existingUtilisateur);

        } else {
            System.out.println(" Erreur : Aucun utilisateur trouvé avec l'id " + id);
            return null;
        }
    }



    @Override
    public String deleteById(int id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("L'utilisateur avec l'id: " +id+" non trouvé");
        }
        utilisateurRepository.deleteById(id);
        return "L'utilisateur a été supprimé avec succès !";
    }

    @Override
    public void deleteAll() {
        utilisateurRepository.deleteAll();
    }
}
