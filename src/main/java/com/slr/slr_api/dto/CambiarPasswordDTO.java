package com.slr.slr_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CambiarPasswordDTO {
    @NotBlank
    private String nuevaPassword;
}
