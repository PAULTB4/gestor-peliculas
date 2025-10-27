package com.fiis.peliculas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fiis.peliculas.entities.Resena;
import java.util.List;

public interface IResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByPeliculaIdOrderByFechaCreacionDesc(Long peliculaId);
}