package dev.sn.web;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.Utilisateur;
import dev.sn.entities.enums.Role;
import dev.sn.service.interfaces.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor

public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<UtilisateurDto>> getAllUtilisateurs() {
        List<UtilisateurDto> users = utilisateurService.findAll();
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users); // 200 OK avec la liste en body
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDto> getUtilisateurById(@PathVariable int id) {
        utilisateurService.findById(id);
        if (utilisateurService.findById(id).isPresent()) {
            return ResponseEntity.ok(utilisateurService.findById(id).get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<UtilisateurDto>> searchUtilisateur(@RequestParam String nom) {
        return ResponseEntity.ok(utilisateurService.findByName(nom));
    }

    @GetMapping("/search1")
    public ResponseEntity<List<UtilisateurDto>> searchUtilisateurByRole(@RequestParam Role role) {
        return ResponseEntity.ok(utilisateurService.findByRole(role));
    }

    @PostMapping
    public ResponseEntity<UtilisateurDto> createUtilisateur(@RequestBody UtilisateurDto utilisateurDto) {
        utilisateurDto = utilisateurService.create(utilisateurDto);
        return ResponseEntity.ok(utilisateurDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDto> updateUtilisateur(
            @PathVariable int id,
            @RequestBody UtilisateurDto utilisateurDto) {
        UtilisateurDto updatedUser = utilisateurService.update(id, utilisateurDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

