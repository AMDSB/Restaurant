package dev.sn.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuDto {

    private Long id;
    private String name;
    private String description;
}
