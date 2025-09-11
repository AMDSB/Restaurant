package dev.sn.repositories;

import dev.sn.dtos.UtilisateurDto;
import dev.sn.entities.Utilisateur;
import dev.sn.entities.enums.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);

    List<Utilisateur> findByNom(String nom);

    List<Utilisateur> findByRole(Role role);

//    public UtilisateurRepository() {}
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public Utilisateur save(Utilisateur utilisateur) {
//        em.persist(utilisateur);
//        return utilisateur;
//    }
//
//
//    @Override
//    public Utilisateur getById(Integer integer) {
//        return em.find(Utilisateur.class, integer);
//    }
//
//    public Utilisateur update(Utilisateur utilisateur) {
//        return em.merge(utilisateur);
//    }
//
//
//    @Override
//    public List<Utilisateur> findAll() {
//        return List.of();
//    }
//
//
//    @Override
//    public void deleteById(Integer integer) {
//        em.remove(em.find(Utilisateur.class, integer));
//    }
//
//    @Override
//    public void delete(Utilisateur user) {
//        em.remove(user);
//    }
//

}
