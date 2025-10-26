package com.fiis.peliculas.entities;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "actores")
public class Actor implements Serializable {
    private static final long serialVersionUID = -5993984638673000666L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    @Column(name = "url_imagen")
    private String urlImagen;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }


}
