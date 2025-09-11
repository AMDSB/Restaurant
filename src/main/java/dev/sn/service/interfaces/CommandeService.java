package dev.sn.service.interfaces;

import dev.sn.dtos.CommandeDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CommandeService {

    CommandeDto create(CommandeDto commande);

    List<CommandeDto> getAll();

    Optional<CommandeDto> getById(int id);

    CommandeDto update(int id, CommandeDto commande);

    void delete(int id);

    List<CommandeDto> searchByDate(Date date);

}

