package dev.sn.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@jakarta.persistence.Table(name = "tables")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Table {
//    @Setter
//    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String numero;
    @Column(nullable = false)
    private int place;

    private String zone; // Terrasse, Salle principale, VIP, etc.

    @Column(nullable = false)
    private Boolean disponible = true;

//    @OneToMany(mappedBy = "table")
//    private List<Commande> commandes = new ArrayList<>();
//    @OneToMany(mappedBy = "table")
//    private List<Commande> commandes = new ArrayList<>();


}
