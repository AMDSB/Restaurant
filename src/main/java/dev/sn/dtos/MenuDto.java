package dev.sn.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuDto {

    private Long id;
    private String name;
    private String description;
    private List<PlatDto> plats;
}
