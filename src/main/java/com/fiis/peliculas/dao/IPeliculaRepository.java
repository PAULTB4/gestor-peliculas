package com.fiis.peliculas.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fiis.peliculas.entities.Pelicula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {
    @Query("SELECT DISTINCT p FROM Pelicula p " +
            "LEFT JOIN p.protagonistas a " +
            "WHERE (:nombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
            "AND (:generoId IS NULL OR p.genero.id = :generoId) " +
            "AND (:actorId IS NULL OR a.id = :actorId)")
    Page<Pelicula> buscarConFiltros(
            @Param("nombre") String nombre,
            @Param("generoId") Long generoId,
            @Param("actorId") Long actorId,
            Pageable pageable);
}
