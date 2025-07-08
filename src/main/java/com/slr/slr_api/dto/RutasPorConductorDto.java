package com.slr.slr_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RutasPorConductorDto {
    private String conductor;
    private Long totalRutas;
}
