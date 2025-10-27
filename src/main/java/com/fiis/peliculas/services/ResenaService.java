package com.fiis.peliculas.services;

import com.fiis.peliculas.dao.IResenaRepository;
import com.fiis.peliculas.entities.Resena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResenaService implements IResenaService {

    @Autowired
    private IResenaRepository repo;

    @Override
    public void save(Resena resena) {
        repo.save(resena);
    }

    @Override
    public List<Resena> findByPeliculaId(Long peliculaId) {
        return repo.findByPeliculaIdOrderByFechaCreacionDesc(peliculaId);
    }

    @Override
    public Resena findById(Long id) {
        return repo.findById(id).orElse(null);
    }
}