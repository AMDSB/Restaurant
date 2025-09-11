package dev.sn.service.interfaces;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.Utilisateur;
import dev.sn.entities.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService  {


    UtilisateurDto create(UtilisateurDto utilisateurDto);

    List<UtilisateurDto> findAll();

    List<UtilisateurDto> findByName(String nom);

    Optional<UtilisateurDto> findByEmail(String email);

    Optional<UtilisateurDto> findById(int id);

    String deleteById(int id);

    List<UtilisateurDto> findByRole(Role role);

    UtilisateurDto update(int id, UtilisateurDto utilisateurDto);
}
