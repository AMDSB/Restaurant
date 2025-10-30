package dev.sn.mappers;

import dev.sn.dtos.PlatDto;
import dev.sn.entities.Plat;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlatMapper {
    PlatDto toPlatDto(Plat plat);

    Plat toPlat(PlatDto platDto);
    void updatePlatFromDto(PlatDto dto, @MappingTarget Plat entity);

    @AfterMapping
    default void setDefaultDisponible(PlatDto dto, @MappingTarget Plat plat) {
        if (plat.getDisponible() == null) {
            plat.setDisponible(true);
        }
    }
}

