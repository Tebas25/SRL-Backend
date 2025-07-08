package com.slr.slr_api.dto;

import lombok.Data;

@Data
public class RutaDTO {
    private String nombre;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private Integer conductorId;
}
