package dev.sn.service.interfaces;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {


    UtilisateurDto create(UtilisateurDto utilisateurDto);

    List<UtilisateurDto> findAll();

    Optional<UtilisateurDto> findById(int id);


    String deleteById(int id);

    void deleteAll();

    UtilisateurDto update(int id, UtilisateurDto utilisateurDto);
}
