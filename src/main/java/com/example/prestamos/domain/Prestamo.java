package com.example.prestamos.domain;

import com.example.prestamos.domain.enumerations.PrestamoEstado;
import com.example.prestamos.domain.vo.DocumentoIdentificativo;
import com.example.prestamos.domain.vo.Importe;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Información del dominio: Prestamo
 *
 * @author Fj migueles
 */
@Getter
@Setter
public class Prestamo implements Serializable {

    /** Identificacion del prestamo */
    private Long id;

    /** Nombre de la persona solicitante del prestamo */
    private String nombreSolicitante;

    /** Documento identificativo(DNI,pasaporte, NIE) */
    private DocumentoIdentificativo documentoIdentificativo;

    /** Importe del prestamo solicitado */
    private Importe importe;

    /** Estado del prestamo */
    private PrestamoEstado estado;

    /** Fecha de creacion del Prestamo */
    private LocalDate fechaCreacion;


    /**
     * Regla de negocio para saber si cambiar de estado es correcto
     * @param nuevoEstado
     */
    public void cambiarEstado(PrestamoEstado nuevoEstado) {
        if (!estado.puedeCambiarA(nuevoEstado)) {
            throw new IllegalStateException(
                    "No se puede cambiar de " + estado + " a " + nuevoEstado
            );
        }
        this.estado = nuevoEstado;
    }
}
