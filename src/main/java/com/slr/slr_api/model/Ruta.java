package com.slr.slr_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    @ManyToOne
    @JoinColumn(name = "conductor_id", nullable = false)
    private Conductor conductor;

    @Column(nullable = false)
    private String distancia;

    @Column(nullable = false)
    private String placa;

}
