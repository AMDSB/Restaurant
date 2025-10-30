package dev.sn.web;

import dev.sn.dtos.CommandeDto;
import dev.sn.dtos.LigneCommandeDto;
import dev.sn.service.interfaces.CommandeService;
import dev.sn.service.interfaces.LigneCommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/commandes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommandeController {
//    final private CommandeService commandeService;

    private final CommandeService commandeService;
    private final LigneCommandeService ligneCommandeService;

    @GetMapping
    public List<CommandeDto> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public CommandeDto getCommandeById(@PathVariable Long id) {
        return commandeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
    }


    @PostMapping
    public CommandeDto create(@RequestBody CommandeDto dto) {
        return commandeService.createCommande(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCommande(@PathVariable Long id) {
        String message = commandeService.deleteById(id);
        return ResponseEntity.ok(message);
    }



    @PostMapping("/{commandeId}/plats/{platId}")
    public LigneCommandeDto addPlat(
            @PathVariable Long commandeId,
            @PathVariable Long platId,
            @RequestParam int quantite) {
        return ligneCommandeService.addPlatToCommande(commandeId, platId, quantite);
    }

    @PutMapping("/lignes/{ligneId}")
    public LigneCommandeDto updateQuantite(
            @PathVariable Long ligneId,
            @RequestParam int quantite) {
        return ligneCommandeService.updateQuantite(ligneId, quantite);
    }

    @DeleteMapping("/lignes/{ligneId}")
    public void deleteLine(@PathVariable Long ligneId) {
        ligneCommandeService.removeLigne(ligneId);
    }

    @GetMapping("/{commandeId}/lignes")
    public List<LigneCommandeDto> getLines(@PathVariable Long commandeId) {
        return ligneCommandeService.findByCommandeId(commandeId);
    }

//    @PutMapping("/{commandeId}/status")
//    public CommandeDto updateStatus(
//            @PathVariable Long commandeId,
//            @RequestParam String status) {
//        return commandeService.updateStatus(commandeId, status);
//    }

    @PatchMapping("/{commandeId}/status")
    public CommandeDto updateStatus(
            @PathVariable Long commandeId,
            @RequestParam String status) {
        return commandeService.updateStatus(commandeId, status);
    }

//    pour le front

//    @PatchMapping("/{commandeId}/status")
//    public CommandeDto updateStatus(
//            @PathVariable Long commandeId,
//            @RequestBody Map<String, String> payload) {
//        String status = payload.get("status");
//        return commandeService.updateStatus(commandeId, status);
//    }

























//
//    @PostMapping
//    public ResponseEntity<CommandeDto> createCommande(@RequestBody CommandeDto commandeDto) {
//        CommandeDto created = commandeService.createCommande(commandeDto);
//        return ResponseEntity.ok(created);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CommandeDto>> getAllCommandes() {
//        List<CommandeDto> commandes = commandeService.getAllCommandes();
//        return ResponseEntity.ok(commandes);
//    }
//
////    @GetMapping("/{id}")
////    public ResponseEntity<Optional<CommandeDto>> findById(@PathVariable Long id) {
////        Optional<CommandeDto> commandeDto = commandeService.findById(id);
////        return ResponseEntity.ok(commandeDto);
////    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<CommandeDto> findById(@PathVariable Long id) {
//        return commandeService.findById(id)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CommandeDto> updateCommande(
//            @PathVariable Long id,
//            @RequestBody CommandeDto commandeDto
//    ) {
//        CommandeDto updated = commandeService.updateCommande(id, commandeDto);
//        return ResponseEntity.ok(updated);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
//        commandeService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}
