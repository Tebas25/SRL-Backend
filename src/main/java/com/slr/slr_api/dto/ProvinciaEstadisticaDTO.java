package com.slr.slr_api.dto;

public class ProvinciaEstadisticaDTO {
    private String provincia;
    private Long total;

    public ProvinciaEstadisticaDTO(String provincia, Long total) {
        this.provincia = provincia;
        this.total = total;
    }

    public String getProvincia() { return provincia; }
    public Long getTotal() { return total; }
}
