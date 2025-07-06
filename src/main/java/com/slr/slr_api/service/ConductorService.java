package com.slr.slr_api.service;

import com.slr.slr_api.dto.ConductorRegistroDTO;
import com.slr.slr_api.dto.ConductorUpdateDTO;
import com.slr.slr_api.model.Conductor;
import com.slr.slr_api.model.Usuario;
import com.slr.slr_api.repository.ConductorRepository;
import com.slr.slr_api.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConductorService {

    private final ConductorRepository conductorRepository;
    private final UsuarioRepository usuarioRepository;

    public Optional<Conductor> buscarPorUsuarioId(int usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .flatMap(conductorRepository::findByUsuario);
    }

    public List<Conductor> listarConductores() {
        return conductorRepository.findAll();
    }

    public Conductor registrarNuevoConductor(ConductorRegistroDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese email.");
        }

        // Crear usuario básico
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(dto.getNombre());
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setPasswordHash(dto.getPassword());
        nuevoUsuario.setTipoUsuario("conductor");

        Usuario guardado = usuarioRepository.save(nuevoUsuario);

        // Crear conductor completo
        Conductor conductor = new Conductor();
        conductor.setUsuario(guardado);
        conductor.setLicencia(dto.getLicencia());
        conductor.setProvincia(dto.getProvincia());
        conductor.setTipoContrato(dto.getTipoContrato());

        return conductorRepository.save(conductor);
    }



    public List<Conductor> buscarConductoresPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return conductorRepository.findAll();
        }
        return conductorRepository.findByUsuarioNombreContainingIgnoreCase(nombre);
    }

    public Conductor actualizarConductor(int id, ConductorUpdateDTO dto) {
        Conductor conductor = conductorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Conductor no encontrado"));

        Usuario usuario = conductor.getUsuario();

        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            usuario.setNombre(dto.getNombre());
        }

        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            usuario.setEmail(dto.getEmail());
        }

        if (dto.getLicencia() != null && !dto.getLicencia().isBlank()) {
            conductor.setLicencia(dto.getLicencia());
        }

        usuarioRepository.save(usuario);
        return conductorRepository.save(conductor);
    }

    public List<Map<String, Object>> obtenerEstadisticasPorProvincia() {
        List<Object[]> resultados = conductorRepository.contarPorProvincia();
        return resultados.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("provincia", obj[0]);
            map.put("total", obj[1]);
            return map;
        }).toList();
    }

    public List<Map<String, Object>> obtenerEstadisticasPorTipoContrato() {
        List<Object[]> resultados = conductorRepository.contarPorTipoContrato();
        return resultados.stream().map(obj -> {
            Map<String, Object> map = new HashMap<>();
            map.put("tipoContrato", obj[0]);
            map.put("total", obj[1]);
            return map;
        }).toList();
    }
}
