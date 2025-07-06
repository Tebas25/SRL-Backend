package com.slr.slr_api.repository;

import com.slr.slr_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByTipoUsuarioIgnoreCase(String tipoUsuario);

}


