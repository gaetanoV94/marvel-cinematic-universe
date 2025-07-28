package com.example.mcu.service;

import com.example.mcu.entity.Actor;
import com.example.mcu.repository.ActorRepository;
import com.example.mcu.specification.ActorSpecifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public Actor findOrCreate(String name, String surname) {
        Specification<Actor> hasFullName = ActorSpecifications.hasFullName(name, surname);
		
        return actorRepository.findOne(hasFullName)
                .orElseGet(() -> {
                    Actor actor = new Actor();
                    actor.setName(name);
                    actor.setSurname(surname);
                    return actorRepository.save(actor);
                });
    }
    
    public List<Actor> filterActors(String name, String surname) {
        return actorRepository.findAll(
            Specification.where(ActorSpecifications.hasName(name)).and(ActorSpecifications.hasSurname(surname))
        );
    }
}
