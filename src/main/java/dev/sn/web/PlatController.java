package dev.sn.web;


import dev.sn.dtos.PlatDto;
import dev.sn.service.interfaces.PlatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/plats")
@CrossOrigin("*")
public class PlatController {
    final private PlatService platService;

    public PlatController(PlatService platService) {
        this.platService = platService;
    }

    @PostMapping
    public PlatDto create(@RequestBody PlatDto platDto){
        return platService.create(platDto);
    }

    @GetMapping
    public List<PlatDto> findAll(){
        return platService.findAll();
    }

    @PutMapping("/{id}")
    public PlatDto update(@PathVariable long id, @RequestBody PlatDto platDto){
        return platService.update(id, platDto);
    }

    @GetMapping("/{id}")
    public Optional<PlatDto> findById(@PathVariable long id){
        return platService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id){
        return platService.deleteById(id);
    }
}
