package com.example.mcu.specification;

import com.example.mcu.entity.Cast;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class CastSpecifications {

    public static Specification<Cast> hasActorName(String name) {
        return (root, query, cb) -> {
            if (name == null) return null;
            Join<Object, Object> actor = root.join("actor");
            return cb.like(cb.lower(actor.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Cast> hasActorSurname(String surname) {
        return (root, query, cb) -> {
            if (surname == null) return null;
            Join<Object, Object> actor = root.join("actor");
            return cb.like(cb.lower(actor.get("surname")), "%" + surname.toLowerCase() + "%");
        };
    }

    public static Specification<Cast> hasCharacter(String character) {
        return (root, query, cb) ->
            character == null ? null : cb.like(cb.lower(root.get("character")), "%" + character.toLowerCase() + "%");
    }

    public static Specification<Cast> hasProductTitle(String title) {
        return (root, query, cb) -> {
            if (title == null) return null;
            Join<Object, Object> product = root.join("product");
            return cb.like(cb.lower(product.get("title")), "%" + title.toLowerCase() + "%");
        };
    }

    public static Specification<Cast> hasPhase(String phaseName) {
        return (root, query, cb) -> {
            if (phaseName == null) return null;
            Join<Object, Object> product = root.join("product");
            Join<Object, Object> phase = product.join("phase");
            return cb.equal(cb.lower(phase.get("name")), phaseName.toLowerCase());
        };
    }
}
