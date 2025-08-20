package dev.sn.entities;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
