package com.example.prestamos.rest.beans;

import com.example.prestamos.domain.enumerations.PrestamoEstado;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class PrestamoResponse {

    private Long id;
    private String nombreSolicitante;
    private String documentoIdentificativo;
    private BigDecimal importe;
    private String divisa;
    private PrestamoEstado estado;
    private LocalDate fechaCreacion;
}
