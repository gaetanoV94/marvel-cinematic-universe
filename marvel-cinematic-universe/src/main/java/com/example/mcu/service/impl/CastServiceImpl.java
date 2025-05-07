package com.example.mcu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcu.entity.Cast;
import com.example.mcu.repository.CastRepository;
import com.example.mcu.service.CastService;

@Service
public class CastServiceImpl implements CastService {
    
	@Autowired
    private CastRepository castRepository;

    @Override
    public Cast saveCast(Cast cast) {
        return castRepository.save(cast);
    }

    @Override
    public List<Cast> getAllCast() {
        return castRepository.findAll().stream()
                .toList();
    }

	@Override
	public List<Cast> findByCharacter(String personaggio) {
		return castRepository.findByCharacter(personaggio).stream()
				.toList();
	}

	@Override
	public List<Cast> findCastByActorFullName(String name, String surname) {
		return castRepository.findCastByActorFullName(name, surname).stream().toList();
	}

	@Override
	public List<Cast> findCastByActorNameOrSurname(String nameOrSurname) {
		return castRepository.findCastByActorNameOrSurname(nameOrSurname).stream().toList();
	}

	@Override
	public List<Cast> findCastByTitle(String title) {
		return castRepository.findCastByTitle(title).stream().toList();
	}

	@Override
	public List<Cast> findCastByTitleLike(String title) {
		return castRepository.findCastByTitleLike(title).stream().toList();
	}
}

