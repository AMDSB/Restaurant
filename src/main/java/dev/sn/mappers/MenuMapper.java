package dev.sn.mappers;

import dev.sn.dtos.MenuDto;
import dev.sn.dtos.PlatDto;
import dev.sn.entities.Menu;
import dev.sn.entities.Plat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuDto toMenuDto(Menu menu);

    @Mapping(target = "id", ignore = true)
    Menu toMenu(MenuDto menuDto);


    PlatDto toPlatDto(Plat plat);
}
