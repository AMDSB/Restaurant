package dev.sn.entities;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private Role role;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

}
