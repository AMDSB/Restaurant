package dev.sn.entities;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String name;
    private String description;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
