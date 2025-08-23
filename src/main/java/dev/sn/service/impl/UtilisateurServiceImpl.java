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

        Utilisateur existingUser = utilisateurRepository.findById((int) id)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur avec l'id " + id + " non trouvé"));

        // 2. Vérifier l’email
        if (utilisateurDto.getEmail() != null && !utilisateurDto.getEmail().isBlank()) {
            utilisateurRepository.findByEmail(utilisateurDto.getEmail())
                    .ifPresent(user -> {
                        if (!user.getId().equals(existingUser.getId())) {
                            throw new IllegalArgumentException("Cet email est déjà utilisé par un autre utilisateur");
                        }
                    });
        }

        // 3. Mettre à jour les champs via MapStruct
        utilisateurMapper.updateUtilisateurFromDto(utilisateurDto, existingUser);

        // 4. Sauvegarder
        Utilisateur updatedUser = utilisateurRepository.save(existingUser);

        // 5. Retourner le DTO
        return utilisateurMapper.toUtilisateurDto(updatedUser);
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
