package com.slr.slr_api.controller;

import com.slr.slr_api.dto.ConductorRegistroDTO;
import com.slr.slr_api.dto.ConductorSearchDTO;
import com.slr.slr_api.dto.ConductorUpdateDTO;
import com.slr.slr_api.model.Conductor;
import com.slr.slr_api.service.ConductorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conductores")
@RequiredArgsConstructor
public class ConductorController {

    private final ConductorService conductorService;

    @GetMapping
    public ResponseEntity<List<Conductor>> listar() {
        return ResponseEntity.ok(conductorService.listarConductores());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Conductor> buscarPorUsuarioId(@PathVariable int usuarioId) {
        return conductorService.buscarPorUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarNuevoConductor(@Valid @RequestBody ConductorRegistroDTO dto) {
        try {
            return ResponseEntity.ok(conductorService.registrarNuevoConductor(dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<Conductor>> buscarPorNombre(@RequestBody ConductorSearchDTO dto) {
        return ResponseEntity.ok(conductorService.buscarConductoresPorNombre(dto.getNombre()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable int id,
            @RequestBody ConductorUpdateDTO dto
    ) {
        try {
            return ResponseEntity.ok(conductorService.actualizarConductor(id, dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/estadisticas/provincia")
    public ResponseEntity<?> obtenerPorProvincia() {
        return ResponseEntity.ok(conductorService.obtenerEstadisticasPorProvincia());
    }

    @GetMapping("/estadisticas/contrato")
    public ResponseEntity<?> obtenerPorTipoContrato() {
        return ResponseEntity.ok(conductorService.obtenerEstadisticasPorTipoContrato());
    }

}
