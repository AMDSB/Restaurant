package dev.sn.dtos;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String token;

    private long expiresIn;

}
