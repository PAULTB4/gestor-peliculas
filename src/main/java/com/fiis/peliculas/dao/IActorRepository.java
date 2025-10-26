package com.fiis.peliculas.dao;
import org.springframework.data.repository.CrudRepository;
import com.fiis.peliculas.entities.Actor;

public interface IActorRepository extends CrudRepository<Actor, Long> {
}
