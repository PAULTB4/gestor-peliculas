package com.fiis.peliculas.dao;

import com.fiis.peliculas.entities.Genero;

import org.springframework.data.repository.CrudRepository;


public interface IGeneroRepository extends CrudRepository<Genero, Long> {
}
