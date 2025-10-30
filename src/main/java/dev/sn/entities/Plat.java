package dev.sn.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//@Entity
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

//    private String name;
//    private String description;
//    private Double price;

    @Column(nullable = false)
    private String nom;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    private String categorie; // Entrée, Plat principal, Dessert, Boisson, etc.

    private String image;

    @Column(nullable = false)
    private Boolean disponible = true;

    @ManyToMany(mappedBy = "plats")
    private List<Menu> menus = new ArrayList<>();


}
