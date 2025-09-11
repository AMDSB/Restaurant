package dev.sn.web;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.enums.Role;
import dev.sn.service.interfaces.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor

public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<?> getAllUtilisateurs() {
        List<UtilisateurDto> users = utilisateurService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.status(405).body("Aucun utilisateur trouvé");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Il y a " + users.size() + " utilisateur(s).");
        response.put("utilisateurs", users);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUtilisateurById(@PathVariable int id) {
        utilisateurService.findById(id);
        if (utilisateurService.findById(id).isPresent()) {
            return ResponseEntity.ok(utilisateurService.findById(id).get());
        }
        return ResponseEntity.status(404).body("Utilisateur avec l'id "+id+" n'existe pas");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchUtilisateur(@RequestParam String nom) {
        if (nom == null || nom.isEmpty()) {
            return ResponseEntity.status(400).body("Le nom ne doit etre ni vide ni manquant");
        }
        return ResponseEntity.ok(utilisateurService.findByName(nom));
    }

    @GetMapping("/search1")
    public ResponseEntity<?> searchUtilisateurByRole(@RequestParam String role) {
        try {
            Role validRole = Role.valueOf(role.toUpperCase());
            return ResponseEntity.ok(utilisateurService.findByRole(validRole));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    "Le rôle '" + role + "' n'existe pas. Rôles valides : " + Arrays.toString(Role.values())
            );
        }
    }
    @PostMapping
    public ResponseEntity<String> createUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        // Vérification des champs obligatoires
        if (utilisateurDto.getPrenom() == null || utilisateurDto.getPrenom().isBlank()) {
            return ResponseEntity.badRequest().body("Le prénom est obligatoire et ne doit pas être vide.");
        }
        if (utilisateurDto.getNom() == null || utilisateurDto.getNom().isBlank()) {
            return ResponseEntity.badRequest().body("Le nom est obligatoire et ne doit pas être vide.");
        }
        if (utilisateurDto.getEmail() == null || utilisateurDto.getEmail().isBlank()) {
            return ResponseEntity.badRequest().body("L'email est obligatoire et ne doit pas être vide.");
        }
        if (utilisateurDto.getPassword() == null || utilisateurDto.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body("Le mot de passe est obligatoire et ne doit pas être vide.");
        }
        if (utilisateurDto.getRole() == null) {
            return ResponseEntity.badRequest().body("Veuillez renseigner le rôle de l'utilisateur.");
        }

        // Vérification de l'unicité de l'email
        Optional<UtilisateurDto> existingUser = utilisateurService.findByEmail(utilisateurDto.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(409).body("Un utilisateur avec l'email " + utilisateurDto.getEmail() + " existe déjà.");
        }

        // Création de l'utilisateur
        utilisateurService.create(utilisateurDto);
        return ResponseEntity.status(201).body("Utilisateur créé avec succès !");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUtilisateur(@PathVariable int id, @RequestBody UtilisateurDto utilisateurDto) {
        try {
            UtilisateurDto updatedUser = utilisateurService.update(id, utilisateurDto);
            return ResponseEntity.ok(updatedUser); // 200 OK avec le user mis à jour
        } catch (RuntimeException e) {
            // en cas d'erreur : email déjà utilisé, user inexistant
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.status(200).body("Utilisateur supprimé avec succés");
    }
}

