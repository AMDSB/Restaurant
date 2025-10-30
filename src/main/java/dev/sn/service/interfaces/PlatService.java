package dev.sn.service.interfaces;
import dev.sn.dtos.PlatDto;
import java.util.List;
import java.util.Optional;

public interface PlatService {

    PlatDto create(PlatDto platDto);
    List<PlatDto> findAll();
    Optional<PlatDto> findById(long platId);
    PlatDto update(long menuId, PlatDto platDto);
    String deleteById(long platId);
    String deleteAll();
}
