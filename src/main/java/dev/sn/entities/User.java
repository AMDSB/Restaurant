package dev.sn.entities;

import dev.sn.entities.enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;
}
