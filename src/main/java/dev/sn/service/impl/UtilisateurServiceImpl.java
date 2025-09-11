package dev.sn.service.impl;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.Utilisateur;
import dev.sn.entities.enums.Role;
import dev.sn.mappers.UtilisateurMapper;
import dev.sn.repositories.UtilisateurRepository;
import dev.sn.service.interfaces.UtilisateurService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service

public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, UtilisateurMapper utilisateurMapper) {
        this.utilisateurRepository = utilisateurRepository;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll()
                .stream()
                .filter(utilisateur -> !utilisateur.isDeleted()) // vérifie directement ici si l'utilisateur est supprimé
                .map(utilisateurMapper::toUtilisateurDto)
                .collect(Collectors.toList());
    }

    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        // Vérification si email déjà présent
        Optional<Utilisateur> existingUser = utilisateurRepository.findByEmail(utilisateurDto.getEmail());

        if (existingUser.isPresent() && !existingUser.get().isDeleted()) {
            System.out.println(" Erreur : un utilisateur avec l'email "
                    + utilisateurDto.getEmail() + " existe déjà !");
            return null; // ou lever une exception custom si tu veux
        }else {

            // Si l'email est unique, on enregistre
            Utilisateur utilisateur = utilisateurMapper.toUtilisateur(utilisateurDto);
            Utilisateur savedUser = utilisateurRepository.save(utilisateur);

            System.out.println(" Utilisateur créé avec succès ");
            return utilisateurMapper.toUtilisateurDto(savedUser);
        }
    }

    @Override
    public UtilisateurDto create(UtilisateurDto utilisateurDto) {
        return save(utilisateurDto);
    }

    @Override
    public List<UtilisateurDto> findByName(String nom) {
        if (nom == null || nom.isBlank()) {
            System.out.println("Erreur : le nom fourni est vide ou invalide.");
            return new ArrayList<>(); // retourne une liste vide
        }

        List<Utilisateur> utilisateurs = utilisateurRepository.findByNom(nom.trim());

        if (utilisateurs.isEmpty()) {
            System.out.println(" Aucun utilisateur trouvé avec le nom : " + nom);
            return new ArrayList<>();
        }
        return utilisateurs
                .stream()
                .filter(utilisateur -> !utilisateur.isDeleted()) // filtre les utilisateurs supprimés
                .map(utilisateurMapper::toUtilisateurDto)
                .toList();
    }

    @Override
    public Optional<UtilisateurDto> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .map(utilisateurMapper::toUtilisateurDto);
    }

    @Override
    public Optional<UtilisateurDto> findById(int id) {
        return utilisateurRepository.findById(id)
                .filter(utilisateur -> !utilisateur.isDeleted())
                .map(utilisateurMapper::toUtilisateurDto);
    }

    @Override
    public List<UtilisateurDto> findByRole(Role role) {

        List<Utilisateur> users = utilisateurRepository.findByRole(role);

        return users
                .stream()
                .filter(utilisateur -> !utilisateur.isDeleted()) // filtre les utilisateurs supprimés
                .map(utilisateurMapper::toUtilisateurDto)
                .toList();
    }

    @Override
    public UtilisateurDto update(int id, UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur avec l'id " + id + " n'existe pas."));

        // Vérif email (ignorer si c'est le même user)
        if (utilisateurDto.getEmail() != null && !utilisateurDto.getEmail().isBlank()) {
            Optional<Utilisateur> existingUser = utilisateurRepository.findByEmail(utilisateurDto.getEmail());
            if (existingUser.isPresent()) {
                existingUser.get();
                throw new RuntimeException("Erreur : un utilisateur avec l'email " + utilisateurDto.getEmail() + " existe déjà !");
            }
            utilisateur.setEmail(utilisateurDto.getEmail());
        }

        // Mise à jour des autres champs
        if (utilisateurDto.getPrenom() != null && !utilisateurDto.getPrenom().isBlank()) {
            utilisateur.setPrenom(utilisateurDto.getPrenom());
        }
        if (utilisateurDto.getNom() != null && !utilisateurDto.getNom().isBlank()) {
            utilisateur.setNom(utilisateurDto.getNom());
        }
        if (utilisateurDto.getPassword() != null && !utilisateurDto.getPassword().isBlank()) {
            utilisateur.setPassword(utilisateurDto.getPassword());
        }
        if (utilisateurDto.getRole() != null) {
            utilisateur.setRole(utilisateurDto.getRole());
        }

        Utilisateur updated = utilisateurRepository.save(utilisateur);
        return utilisateurMapper.toUtilisateurDto(updated);
    }

    @Override
    public String deleteById (int id){
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("L'utilisateur avec l'id: " + id + " non trouvé");
        }
        Utilisateur user = utilisateurRepository.findById(id).get();
        user.setIsDeleted(true);
        return "Utilisateur supprimé avec succes";
    }

}
