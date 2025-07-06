package com.slr.slr_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioTipoFiltroDTO {
    @NotBlank
    private String tipoUsuario;
}
