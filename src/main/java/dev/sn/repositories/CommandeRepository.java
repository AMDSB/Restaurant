package dev.sn.repositories;

import dev.sn.entities.Commande;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CommandeRepository implements JpaRepository<Commande, Integer> {

    public CommandeRepository() {}

    @PersistenceContext
    private EntityManager em;

    public Commande save(Commande commande){
        em.persist(commande);
        return commande;
    }

}
