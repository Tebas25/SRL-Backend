package com.slr.slr_api.dto;

import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Data
public class UsuarioLoginDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
