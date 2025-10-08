package dev.sn.dtos;

import dev.sn.entities.enums.Role;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User1Dto {

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;
    private String password;

}
