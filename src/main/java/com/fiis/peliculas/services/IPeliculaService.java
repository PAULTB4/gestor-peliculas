package com.fiis.peliculas.services;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import com.fiis.peliculas.entities.Pelicula;

public interface IPeliculaService {
    public void save(Pelicula pelicula);
    public Pelicula findById(Long id);
    public List<Pelicula> findAll();
    public Page<Pelicula> findAll(Pageable pegable);
    public void delete(Long id);
    public Page<Pelicula> buscarConFiltros(String nombre, Long generoId, Long actorId, Pageable pageable);
}
