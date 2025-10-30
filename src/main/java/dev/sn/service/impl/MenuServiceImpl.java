package dev.sn.service.impl;

import dev.sn.dtos.MenuDto;
import dev.sn.entities.Menu;
import dev.sn.mappers.MenuMapper;
import dev.sn.repositories.MenuRepository;
import dev.sn.repositories.PlatRepository;
import dev.sn.service.interfaces.MenuService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    final private MenuRepository menuRepository;
    final private MenuMapper menuMapper;
    final private PlatRepository platRepository;

    public MenuServiceImpl(MenuRepository menuRepository,
                           MenuMapper menuMapper,
                           PlatRepository platRepository) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
        this.platRepository = platRepository;
    }

    private MenuDto save(MenuDto menuDto){
        Menu menu = menuMapper.toMenu(menuDto);
//        Menu menuSaved = menuRepository.save(menu);
//        return menuMapper.toMenuDto(menuSaved);

        if (menuDto.getPlats() != null ){
            menu.getPlats().clear();
            menuDto.getPlats().forEach(platDto -> {
                platRepository.findById(platDto.getId())
                        .ifPresent(menu.getPlats()::add);
            });
        }


        return menuMapper.toMenuDto(menuRepository.save(menu));

    }

    @Override
    public MenuDto create(MenuDto menuDto) {
        return save(menuDto);
    }

    @Override
    public List<MenuDto> findAll() {
        return menuRepository.findAll().stream()
                .map(menuMapper::toMenuDto).
                collect(Collectors.toList());
    }



    @Override
    public Optional<MenuDto> findById(long menuId) {
//        Optional<Menu> menuOptional = menuRepository.findById(menuId);
//        return menuOptional.map(menu -> MenuDto.builder()
//                .id(menu.getId())
//                .name(menu.getName())
//                .description(menu.getDescription())
//                .build());
        return menuRepository.findById(menuId)
                .map(menuMapper::toMenuDto);
    }

//    public Optional<MenuDto> findById(long menuId) {
//        return menuRepository.findById(menuId)
//                .map(menuMapper::toMenuDto);
//    }


    @Override
    public MenuDto update(long menuId, MenuDto menuDto) {
        // Récupérer le menu existant ou lever une exception
        MenuDto existingMenuDto = findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé avec id: " + menuId));

        // Mettre à jour le nom
        if (menuDto.getName() != null) {
            existingMenuDto.setName(menuDto.getName());
        }

        // Mettre à jour la description
        if (menuDto.getDescription() != null) {
            existingMenuDto.setDescription(menuDto.getDescription());
        }

        // Mettre à jour les plats
        if (menuDto.getPlats() != null) {
            existingMenuDto.getPlats().clear();
            menuDto.getPlats().forEach(platDto -> {
                platRepository.findById(platDto.getId())
                        .ifPresent(plat -> existingMenuDto.getPlats().add(menuMapper.toPlatDto(plat)));
            });
        }


        // Sauvegarder le menu avec les modifications
        return save(existingMenuDto);
    }

//    @Override
//    public MenuDto update(long menuId, MenuDto menuDto) {
//        Optional<MenuDto> menuDtoOptional = findById(menuId);
//        if (menuDtoOptional.isPresent()){
//            MenuDto newMenudto = menuDtoOptional.get();
//            if (menuDto.getName()!=null){
//                newMenudto.setName(menuDto.getName());
//            }
//
//            if (menuDto.getDescription()!=null){
//                newMenudto.setDescription(menuDto.getDescription());
//            }
//
//            if (menuDto.getPlats() != null) {
//                newMenudto.getPlats().clear();
//                menuDto.getPlats().forEach(platDto -> {
//                    platRepository.findById(platDto.getId())
//                            .ifPresent(newMenudto.getPlats()::add);
//                });
//            }
//
//            return save(newMenudto);
//        }
//        return null;
//    }

//    public MenuDto update(long menuId, MenuDto menuDto) {
//        return menuRepository.findById(menuId)
//                .map(existingMenu -> {
//                    if (menuDto.getName() != null) {
//                        existingMenu.setName(menuDto.getName());
//                    }
//                    if (menuDto.getDescription() != null) {
//                        existingMenu.setDescription(menuDto.getDescription());
//                    }
//                    Menu updatedMenu = menuRepository.save(existingMenu);
//                    return menuMapper.toMenuDto(updatedMenu);
//                })
//                .orElseThrow(() -> new RuntimeException("Menu non trouvé avec id: " + menuId));
//    }


    @Override
//    public String deleteById(long menuId) {
//        menuRepository.deleteById(menuId);
//        return "Le menu a été supprimé avec succès !";
//    }
    public String deleteById(long menuId) {
        if (!menuRepository.existsById(menuId)) {
            throw new RuntimeException("Menu non trouvé avec id: " + menuId);
        }
        menuRepository.deleteById(menuId);
        return "Le menu a été supprimé avec succès !";
    }

    @Override
    public String deleteAll() {
        menuRepository.deleteAll();
        return "La liste des menus a été supprimée avec succès !";
    }

    @Override
    public MenuDto addPlatToMenu(long menuId, long platId){
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé"));
        platRepository.findById(platId)
                .ifPresentOrElse(
                        plat -> menu.getPlats().add(plat),
                        () ->{throw new RuntimeException("Plat non trouvé");
                        }
                );
        return menuMapper.toMenuDto(menuRepository.save(menu));
    }

    @Override
    public MenuDto removePlatFromMenu(long menuId, long platId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("Menu non trouvé"));
        menu.getPlats().removeIf(p -> p.getId().equals(platId));
        return menuMapper.toMenuDto(menuRepository.save(menu));
    }

//    Ajout de méthodes pour gérer un menu comme dans un vrai resto:
//
//    @Override
//    public MenuDto addPlatToMenu(long menuId, long platId) {
//        Menu menu = menuRepository.findById(menuId)
//                .orElseThrow(() -> new RuntimeException("Menu not found"));
//
//        Plat plat = platRepository.findById(platId)
//                .orElseThrow(() -> new RuntimeException("Plat not found"));
//
//        menu.getPlats().add(plat);
//        return menuMapper.toDto(menuRepository.save(menu));
//    }
//
//    @Override
//    public MenuDto removePlatFromMenu(long menuId, long platId) {
//        Menu menu = menuRepository.findById(menuId)
//                .orElseThrow(() -> new RuntimeException("Menu not found"));
//
//        menu.getPlats().removeIf(p -> p.getId().equals(platId));
//        return menuMapper.toDto(menuRepository.save(menu));
//    }

}

