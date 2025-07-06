package com.slr.slr_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false)
    private String licencia;

    @Column(nullable = false)
    private String provincia;

    @Column(nullable = false)
    private String tipoContrato;
}
