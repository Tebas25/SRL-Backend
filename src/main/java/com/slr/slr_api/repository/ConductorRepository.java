package com.slr.slr_api.repository;

import com.slr.slr_api.model.Conductor;
import com.slr.slr_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
    Optional<Conductor> findByUsuario(Usuario usuario);
    List<Conductor> findByUsuarioNombreContainingIgnoreCase(String nombre);

    @Query("SELECT c.provincia AS provincia, COUNT(c) AS total FROM Conductor c GROUP BY c.provincia")
    List<Object[]> contarPorProvincia();

    @Query("SELECT c.tipoContrato AS tipoContrato, COUNT(c) AS total FROM Conductor c GROUP BY c.tipoContrato")
    List<Object[]> contarPorTipoContrato();

}
