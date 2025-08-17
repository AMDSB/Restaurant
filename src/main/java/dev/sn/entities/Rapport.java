package dev.sn.entities;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Entity
@RequiredArgsConstructor

public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String type;
    private Date dateGeneration;

}
