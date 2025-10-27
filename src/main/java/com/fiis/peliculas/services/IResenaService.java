package com.fiis.peliculas.services;

import com.fiis.peliculas.entities.Resena;
import java.util.List;

public interface IResenaService {
    void save(Resena resena);
    List<Resena> findByPeliculaId(Long peliculaId);
    Resena findById(Long id);
}