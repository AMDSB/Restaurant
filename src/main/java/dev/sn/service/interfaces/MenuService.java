package dev.sn.service.interfaces;

import dev.sn.dtos.MenuDto;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    MenuDto create(MenuDto menuDto);
    List<MenuDto> findAll();
    Optional<MenuDto> findById(long menuId);
    MenuDto update(long menuId, MenuDto menuDto);
    String deleteById(long menuId);
    String deleteAll();

    MenuDto addPlatToMenu(long menuId, long platId);

    MenuDto removePlatFromMenu(long menuId, long platId);
}
