package com.fiis.peliculas.dao;
import org.springframework.data.repository.CrudRepository;
import com.fiis.peliculas.entities.Pelicula;

public interface IPeliculaRepository extends CrudRepository<Pelicula, Long> {
}
