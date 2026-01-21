package com.example.prestamos.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * VO del importe del prestamo
 *
 * @author Fj migueles
 */
@Getter
@Setter
public class Importe {

    /** Cantidad del importe del prestamo */
    private BigDecimal cantidad;
    /** Divisa del importe del prestamo */
    private Divisa divisa;

    /**
     * Constructor: Regla de negocio del importe a aplicar
     * @param cantidad
     * @param divisa
     */
    public Importe(BigDecimal cantidad, Divisa divisa) {
        if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El importe no puede ser 0 o negativo");
        }
        this.cantidad = cantidad;
        this.divisa = divisa;

    }


}
