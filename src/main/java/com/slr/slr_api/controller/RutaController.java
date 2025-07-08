package com.slr.slr_api.controller;

import com.slr.slr_api.dto.RutaDTO;
import com.slr.slr_api.model.Ruta;
import com.slr.slr_api.service.RutaService;
import com.slr.slr_api.dto.RutasPorConductorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rutas")
@RequiredArgsConstructor
public class RutaController {

    private final RutaService rutaService;

    @GetMapping
    public List<Ruta> listarRutas() {
        return rutaService.listarRutas();
    }

    @PostMapping
    public Ruta crearRuta(@RequestBody RutaDTO dto) {
        return rutaService.crearRuta(dto);
    }

    @GetMapping("/reporte/rutas-por-provincia")
    public Map<String, Long> obtenerRutasPorProvincia() {
        return rutaService.obtenerConteoRutasPorProvincia();
    }

    @GetMapping("/reporte/rutas-por-conductor")
    public List<RutasPorConductorDto> obtenerRutasPorConductor() {
        return rutaService.obtenerConteoRutasPorConductor();
    }

}

