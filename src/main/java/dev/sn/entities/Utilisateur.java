package dev.sn.entities;

import dev.sn.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Data
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String nom;
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private Role role;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    public void setIsDeleted(boolean b) {
        isDeleted = b;
    }

    public boolean isDeleted() {
        return isDeleted;
    }


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

}
