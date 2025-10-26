package com.fiis.peliculas.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fiis.peliculas.entities.Pelicula;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {
}
