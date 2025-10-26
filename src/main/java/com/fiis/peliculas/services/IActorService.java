package com.fiis.peliculas.services;

import com.fiis.peliculas.entities.Actor;
import java.util.List;

public interface IActorService {
    public List<Actor> findAll();
    public List<Actor>findAllById(List<Long>ids);

}
