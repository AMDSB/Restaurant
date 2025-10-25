package dev.sn.service.impl;

import dev.sn.dtos.MenuDto;
import dev.sn.entities.Menu;
import dev.sn.mappers.MenuMapper;
import dev.sn.repositories.MenuRepository;
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

    public MenuServiceImpl(MenuRepository menuRepository, MenuMapper menuMapper) {
        this.menuRepository = menuRepository;
        this.menuMapper = menuMapper;
    }

    private MenuDto save(MenuDto menuDto){
        Menu menu = menuMapper.toMenu(menuDto);
        Menu menuSaved = menuRepository.save(menu);
        return menuMapper.toMenuDto(menuSaved);
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
        Optional<Menu> menuOptional = menuRepository.findById(menuId);
        return menuOptional.map(menu -> MenuDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .build());
    }

//    public Optional<MenuDto> findById(long menuId) {
//        return menuRepository.findById(menuId)
//                .map(menuMapper::toMenuDto);
//    }

    @Override
    public MenuDto update(long menuId, MenuDto menuDto) {
        Optional<MenuDto> menuDtoOptional = findById(menuId);
        if (menuDtoOptional.isPresent()){
            MenuDto newMenudto = menuDtoOptional.get();
            if (menuDto.getName()!=null){
                newMenudto.setName(menuDto.getName());
            }

            if (menuDto.getDescription()!=null){
                newMenudto.setDescription(menuDto.getDescription());
            }
            return save(newMenudto);
        }
        return null;
    }

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
}
