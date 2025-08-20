package dev.sn.repositories;

import dev.sn.entities.Utilisateur;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class UtilisateurRepository implements JpaRepository<Utilisateur, Integer> {

    public UtilisateurRepository() {}

    @PersistenceContext
    private EntityManager em;

    @Override
    public Utilisateur save(Utilisateur utilisateur) {
        em.persist(utilisateur);
        return utilisateur;
    }

    @Override
    public Utilisateur getById(Integer integer) {
        return em.find(Utilisateur.class, integer);
    }

    public Utilisateur update(Utilisateur utilisateur) {
        return em.merge(utilisateur);
    }


    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }


    @Override
    public void deleteById(Integer integer) {
        em.remove(em.find(Utilisateur.class, integer));
    }

    @Override
    public void delete(Utilisateur user) {
        em.remove(user);
    }

    @Override
    public List<Utilisateur> findAll(Sort sort) {
        return List.of();
    }
}
