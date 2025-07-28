package com.example.mcu.specification;

import com.example.mcu.entity.TVShow;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class TVShowSpecifications {

    public static Specification<TVShow> hasTitle(String title) {
        return (root, query, cb) ->
            title == null ? null : cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<TVShow> hasDirector(String director) {
        return (root, query, cb) ->
            director == null ? null : cb.like(cb.lower(root.get("director")), "%" + director.toLowerCase() + "%");
    }

    public static Specification<TVShow> hasPlatform(String platform) {
        return (root, query, cb) ->
            platform == null ? null : cb.like(cb.lower(root.get("platform")), "%" + platform.toLowerCase() + "%");
    }

    public static Specification<TVShow> hasBudgetLessThan(Long budget) {
        return (root, query, cb) ->
            budget == null ? null : cb.lessThan(root.get("budget"), budget);
    }

    public static Specification<TVShow> hasEpisodesGreaterThan(Integer minEpisodes) {
        return (root, query, cb) ->
            minEpisodes == null ? null : cb.greaterThan(root.get("numberOfEpisodes"), minEpisodes);
    }

    public static Specification<TVShow> hasMetacriticMarkGreaterThan(Double mark) {
        return (root, query, cb) ->
            mark == null ? null : cb.greaterThan(root.get("metacriticMark"), mark);
    }

    public static Specification<TVShow> isInPhase(String phaseName) {
        return (root, query, cb) -> {
            if (phaseName == null) return null;
            Join<Object, Object> phase = root.join("phase");
            return cb.equal(cb.lower(phase.get("name")), phaseName.toLowerCase());
        };
    }
}
