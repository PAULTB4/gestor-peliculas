package com.fiis.peliculas.entities;

import java.io.Serializable;
import java.util.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;
import java.util.ArrayList;


@Entity
@Table(name = "peliculas")
public class Pelicula implements Serializable {

    private static final long serialVersionUID = -5967104310370586978L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no debe ser vacio")
    private String nombre;
    @Column(name = "fecha_estreno")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")

    @NotNull(message = "El campo fecha de estreno no debe estar vacio")
    private Date fechaEstreno;

    @NotNull()
    //@OneToOne
    @ManyToOne
    private Genero genero;

    public void setProtagonistas(List<Actor> protagonistas) {
        this.protagonistas = protagonistas;
    }

    public List<Actor> getProtagonistas() {
        return protagonistas;
    }

    @ManyToMany
    private List<Actor> protagonistas;

    private String imagen;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Resena> resenas = new ArrayList<>();

    @Transient
    public Double getCalificacionPromedio() {
        if (resenas == null || resenas.isEmpty()) {
            return 0.0;
        }
        return resenas.stream()
                .mapToInt(Resena::getCalificacion)
                .average()
                .orElse(0.0);
    }

    @Transient
    public Integer getTotalResenas() {
        return resenas != null ? resenas.size() : 0;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public Long getId(){
        return id;
    }

    public Genero getGenero() { return genero; }
    public void setGenero(Genero genero) { this.genero = genero; }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Date fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }
}
