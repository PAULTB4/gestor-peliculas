package com.fiis.peliculas.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiis.peliculas.dao.IActorRepository;
import com.fiis.peliculas.entities.Actor;


@Service
public class ActorService implements IActorService {

    @Autowired
    private IActorRepository repo;

    @Override
    public List<Actor> findAll() {
        return (List<Actor>) repo.findAll();
    }

    @Override
    public List<Actor> findAllById(List<Long> ids) {
        return (List<Actor>) repo.findAllById(ids);
    }

}
