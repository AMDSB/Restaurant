package dev.sn.repositories;

import dev.sn.dtos.CommandeDto;
import dev.sn.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository

public interface CommandeRepository extends JpaRepository<Commande, Integer> {


    List<CommandeDto> findByDate(Date date);
}
