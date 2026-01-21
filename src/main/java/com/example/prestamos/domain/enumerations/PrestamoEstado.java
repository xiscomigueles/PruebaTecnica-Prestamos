package com.example.prestamos.domain.enumerations;

/**
 * Enumerado con las opciones de estado del prestamo
 *
 * @author Fj migueles
 */
public enum PrestamoEstado {
    /** Pendiente de aprobar el presatamo*/
    PENDIENTE,
    /** Prestamo aprobado */
    APROBADA,
    /** Prestamo rechazado */
    RECHAZADA,
    /** Prestamo cancelado */
    CANCELADA;

    /**
     * Regla de negocio del flujo de estado que se puede aplicar
     * @param nuevo
     * @return {@link boolean}
     */
    public boolean puedeCambiarA(PrestamoEstado nuevo) {
        return switch (this) {
            case PENDIENTE -> nuevo == APROBADA || nuevo == RECHAZADA;
            case APROBADA -> nuevo == CANCELADA;
            default -> false;
        };
    }
}
