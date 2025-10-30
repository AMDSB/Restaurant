package dev.sn.service.impl;

import dev.sn.dtos.PlatDto;
import dev.sn.entities.Plat;
import dev.sn.mappers.PlatMapper;
import dev.sn.repositories.PlatRepository;
import dev.sn.service.interfaces.PlatService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlatServiceImpl implements PlatService {
    final private PlatRepository platRepository;
    final private PlatMapper platMapper;

    public PlatServiceImpl(PlatRepository platRepository,
                           PlatMapper platMapper) {
        this.platRepository = platRepository;
        this.platMapper = platMapper;
    }


    private PlatDto save(PlatDto platDto){
        Plat plat = platMapper.toPlat(platDto);
//        plat.setId(null);
        return platMapper.toPlatDto(platRepository.save(plat));

    }


    @Override
    public PlatDto create(PlatDto platDto) {
//        if (platDto.getId() != null) {
//            throw new IllegalArgumentException("ID must be null when creating a Plat");
//        }
        return save(platDto);
    }

    @Override
    public List<PlatDto> findAll() {
        return platRepository.findAll().stream()
                        .map(platMapper::toPlatDto)
                                .collect(Collectors.toList());
    }

    @Override
    public Optional<PlatDto> findById(long platId) {
//        return Optional.empty();
        return platRepository.findById(platId)
                .map(platMapper::toPlatDto);
    }

    @Override
//    public PlatDto update(long menuId, PlatDto platDto) {
//        return platRepository.findById(platDto.getId())
//                .map(existing -> {
//                    platDto.setId(platDto.getId());
//                    return save(platDto);
//                })
//                .orElseThrow(() -> new RuntimeException("Plat non trouvé !"));
//    }

    public PlatDto update(long platId, PlatDto platDto) {

        Plat existingPlat = platRepository.findById(platId)
                .orElseThrow(() -> new RuntimeException("Plat non trouvé avec id: " + platId));

        // ✅ Copier uniquement les champs qui ne sont pas nuls
        if (platDto.getNom() != null)
            existingPlat.setNom(platDto.getNom());
        if (platDto.getDescription() != null)
            existingPlat.setDescription(platDto.getDescription());
        if (platDto.getCategorie() != null)
            existingPlat.setCategorie(platDto.getCategorie());
        if (platDto.getImage() != null)
            existingPlat.setImage(platDto.getImage());
        if (platDto.getDisponible() != null)
            existingPlat.setDisponible(platDto.getDisponible());
        if (platDto.getPrix() != null)
            existingPlat.setPrix(platDto.getPrix());

        return platMapper.toPlatDto(platRepository.save(existingPlat));
    }


    @Override
    public String deleteById(long platId) {
        if (!platRepository.existsById(platId)) {
            return "Plat introuvable";
        }
        platRepository.deleteById(platId);
        return "Plat supprimé avec succès";
    }

    @Override
    public String deleteAll() {
        platRepository.deleteAll();
        return "Tous les plats ont été supprimés";
    }
}
