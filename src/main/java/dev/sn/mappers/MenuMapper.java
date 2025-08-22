package dev.sn.mappers;

import dev.sn.dtos.MenuDto;
import dev.sn.entities.Menu;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuDto toMenuDto(Menu menu);
    Menu toMenu(MenuDto menuDto);
}
