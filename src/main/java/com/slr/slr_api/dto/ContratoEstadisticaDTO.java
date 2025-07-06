// ContratoEstadisticaDTO.java
package com.slr.slr_api.dto;

public class ContratoEstadisticaDTO {
    private String tipoContrato;
    private Long total;

    public ContratoEstadisticaDTO(String tipoContrato, Long total) {
        this.tipoContrato = tipoContrato;
        this.total = total;
    }

    public String getTipoContrato() { return tipoContrato; }
    public Long getTotal() { return total; }
}
