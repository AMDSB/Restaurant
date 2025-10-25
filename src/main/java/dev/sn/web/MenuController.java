package dev.sn.web;

import dev.sn.dtos.MenuDto;
import dev.sn.mappers.MenuMapper;
import dev.sn.repositories.MenuRepository;
import dev.sn.service.interfaces.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menus")
@CrossOrigin("*")
public class MenuController {
    final private MenuService menuService;
//    final private MenuRepository menuRepository;
//    final private MenuMapper menuMapper;

    public MenuController(MenuService menuService,
                          MenuRepository menuRepository,
                          MenuMapper menuMapper) {
        this.menuService = menuService;

//        this.menuRepository = menuRepository;
//        this.menuMapper = menuMapper;
    }

    @PostMapping
    public MenuDto create(@RequestBody MenuDto menuDto){
        return menuService.create(menuDto);
    }

    @GetMapping
    public List<MenuDto> findAll(){
        return menuService.findAll();
    }

    @PutMapping("/{id}")
    public MenuDto update(@PathVariable long id, @RequestBody MenuDto menuDto){
        return menuService.update(id, menuDto);
    }


    @GetMapping("/{id}")
    public Optional<MenuDto> findById(@PathVariable long id){
        return menuService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id){
        return menuService.deleteById(id);
    }
}
