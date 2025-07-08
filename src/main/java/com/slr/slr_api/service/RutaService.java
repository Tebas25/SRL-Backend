package com.slr.slr_api.service;

import com.slr.slr_api.dto.RutaDTO;
import com.slr.slr_api.dto.RutasPorConductorDto;
import com.slr.slr_api.model.Conductor;
import com.slr.slr_api.model.Ruta;
import com.slr.slr_api.repository.ConductorRepository;
import com.slr.slr_api.repository.RutaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RutaService {
    private final RutaRepository rutaRepository;
    private final ConductorRepository conductorRepository;

    public List<Ruta> listarRutas() {
        return rutaRepository.findAll();
    }

    public Ruta crearRuta(RutaDTO dto) {
        Optional<Conductor> conductor = conductorRepository.findById(dto.getConductorId());
        if (conductor.isEmpty()) throw new RuntimeException("Conductor no encontrado");

        Ruta ruta = new Ruta();
        ruta.setNombre(dto.getNombre());
        ruta.setLatitud(dto.getLatitud());
        ruta.setLongitud(dto.getLongitud());
        ruta.setConductor(conductor.get());
        ruta.setDistancia(dto.getDescripcion());
        ruta.setPlaca(dto.getDescripcion());
        return rutaRepository.save(ruta);
    }

    public Map<String, Long> obtenerConteoRutasPorProvincia() {
        List<Ruta> rutas = rutaRepository.findAll();
        return rutas.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getConductor().getProvincia(),
                        Collectors.counting()
                ));
    }

    public List<RutasPorConductorDto> obtenerConteoRutasPorConductor() {
        List<Ruta> rutas = rutaRepository.findAll();

        return rutas.stream()
                .collect(Collectors.groupingBy(
                        r -> r.getConductor().getUsuario().getNombre(),
                        Collectors.counting()
                ))
                .entrySet().stream()
                .map(e -> new RutasPorConductorDto(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
