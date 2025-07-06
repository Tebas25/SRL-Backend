// dto/ConductorSearchDTO.java
package com.slr.slr_api.dto;

import lombok.Data;

@Data
public class ConductorSearchDTO {
    private String nombre; // puede venir vacío para listar todos
}
