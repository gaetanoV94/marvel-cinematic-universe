package com.example.mcu.specification;

import com.example.mcu.entity.Actor;
import org.springframework.data.jpa.domain.Specification;

public class ActorSpecifications {

    public static Specification<Actor> hasName(String name) {
        return (root, query, cb) ->
            name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Actor> hasSurname(String surname) {
        return (root, query, cb) ->
            surname == null ? null : cb.like(cb.lower(root.get("surname")), "%" + surname.toLowerCase() + "%");
    }

    public static Specification<Actor> hasFullName(String name, String surname) {
        return Specification.where(hasName(name)).and(hasSurname(surname));
    }
}
