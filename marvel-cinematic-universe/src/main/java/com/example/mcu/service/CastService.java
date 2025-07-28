package com.example.mcu.service;

import com.example.mcu.entity.Cast;
import com.example.mcu.repository.CastRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.mcu.specification.CastSpecifications.*;

@Service
public class CastService {

    private final CastRepository castRepository;

    public CastService(CastRepository castRepository) {
        this.castRepository = castRepository;
    }

    public List<Cast> filterCast(String actorName, String actorSurname, String character, String title, String phase) {
        Specification<Cast> spec = Specification
                .where(hasActorName(actorName))
                .and(hasActorSurname(actorSurname))
                .and(hasCharacter(character))
                .and(hasProductTitle(title))
                .and(hasPhase(phase));

        return castRepository.findAll(spec);
    }
}
