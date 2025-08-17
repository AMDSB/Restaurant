package dev.sn.repositories;

import dev.sn.entities.Utilisateur;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class UtilisateurRepository implements JpaRepository<Utilisateur, Integer> {
    public UtilisateurRepository() {}

   
    @Override
    public Utilisateur getById(Integer integer) {
        return null;
    }



    @Override
    public Optional<Utilisateur> findById(Integer integer) {
        return Optional.empty();
    }



    @Override
    public List<Utilisateur> findAll() {
        return List.of();
    }




    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Utilisateur entity) {

    }


    @Override
    public List<Utilisateur> findAll(Sort sort) {
        return List.of();
    }


}
