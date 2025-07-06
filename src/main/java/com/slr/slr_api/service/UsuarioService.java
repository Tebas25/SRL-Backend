package com.slr.slr_api.service;

import com.slr.slr_api.dto.UsuarioCreateDTO;
import com.slr.slr_api.dto.UsuarioUpdateDTO;
import com.slr.slr_api.dto.UsuarioLoginDTO;
import com.slr.slr_api.model.Usuario;
import com.slr.slr_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario registrarUsuario(UsuarioCreateDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo.");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordHash(dto.getPassword());
        usuario.setTipoUsuario(dto.getTipoUsuario());

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorId(int id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean autenticar(UsuarioLoginDTO dto) {
        return usuarioRepository.findByEmail(dto.getEmail())
                .map(usuario -> usuario.getPasswordHash().equals(dto.getPassword()))
                .orElse(false);
    }

    public Usuario actualizarUsuario(int id, UsuarioUpdateDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (dto.getNombre() != null) usuario.setNombre(dto.getNombre());
        if (dto.getEmail() != null) usuario.setEmail(dto.getEmail());
        if (dto.getPassword() != null) usuario.setPasswordHash(dto.getPassword());
        if (dto.getTipoUsuario() != null) usuario.setTipoUsuario(dto.getTipoUsuario());

        return usuarioRepository.save(usuario);
    }

    public void eliminarPorId(int id) {
        usuarioRepository.deleteById(id);
    }

    public void cambiarPassword(int id, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        usuario.setPasswordHash(nuevaPassword);
        usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarPorTipo(String tipoUsuario) {
        return usuarioRepository.findByTipoUsuarioIgnoreCase(tipoUsuario);
    }
}
