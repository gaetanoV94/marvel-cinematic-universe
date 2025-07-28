package com.example.mcu.specification;

import com.example.mcu.entity.Movie;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecifications {

    public static Specification<Movie> hasTitle(String title) {
        return (root, query, cb) ->
            title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Movie> hasDirector(String director) {
        return (root, query, cb) ->
            director == null ? null : cb.like(cb.lower(root.get("director")), "%" + director.toLowerCase() + "%");
    }

    public static Specification<Movie> hasGrossGreaterThan(Long gross) {
        return (root, query, cb) ->
            gross == null ? null : cb.greaterThan(root.get("gross"), gross);
    }

    public static Specification<Movie> hasRunningTimeBetween(Integer min, Integer max) {
        return (root, query, cb) -> {
            if (min != null && max != null) return cb.between(root.get("runningTime"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("runningTime"), min);
            if (max != null) return cb.lessThanOrEqualTo(root.get("runningTime"), max);
            return null;
        };
    }

    public static Specification<Movie> hasBudgetLessThan(Long budget) {
        return (root, query, cb) ->
            budget == null ? null : cb.lessThan(root.get("budget"), budget);
    }

    public static Specification<Movie> hasMetacriticMarkGreaterThan(Double mark) {
        return (root, query, cb) ->
            mark == null ? null : cb.greaterThan(root.get("metacriticMark"), mark);
    }

    public static Specification<Movie> isInPhase(String phaseName) {
        return (root, query, cb) -> {
            if (phaseName == null) return null;
            Join<Object, Object> phase = root.join("phase");
            return cb.equal(cb.lower(phase.get("name")), phaseName.toLowerCase());
        };
    }
}
