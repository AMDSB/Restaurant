package dev.sn.web;

import dev.sn.dtos.CommandeDto;
import dev.sn.service.interfaces.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/commandes")


public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public ResponseEntity<List<CommandeDto>> getCommandes() {
        List<CommandeDto> commandes = commandeService.getAll();
        return  ResponseEntity.ok(commandes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<CommandeDto>> getCommandeById(@PathVariable int id) {
        return ResponseEntity.ok(commandeService.getById(id));
    }

    @GetMapping("/{date}")
    public ResponseEntity<List<CommandeDto>> getCommandeByDate(@PathVariable Date date) {
        return ResponseEntity.ok(commandeService.searchByDate(date));
    }

    @PostMapping
    public ResponseEntity addCommande(@RequestBody CommandeDto commandeDto) {

        if(commandeDto.getServeur() == null){
            return ResponseEntity.status(450).body("Veuillez renseigner le serveur en charge de la commande");
        }
        if(commandeDto.getStatutCommande() == null){
            return ResponseEntity.status(450).body("Veuillez renseigner le statut de la commande");
        }
        commandeService.create(commandeDto);
        return ResponseEntity.status(201).body(commandeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommandeDto> updateCommande(@PathVariable int id, @RequestBody CommandeDto commandeDto) {
        return ResponseEntity.status(450).body(commandeService.update(id, commandeDto));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCommande(@PathVariable int id) {
        commandeService.delete(id);
        return ResponseEntity.status(200).body("Suppression ok");
    }

}
