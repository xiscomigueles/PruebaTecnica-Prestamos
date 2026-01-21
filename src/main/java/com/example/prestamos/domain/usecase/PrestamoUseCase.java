package com.example.prestamos.domain.usecase;

import com.example.prestamos.domain.Prestamo;
import com.example.prestamos.domain.enumerations.PrestamoEstado;

import java.util.Collection;
import java.util.Optional;

/**
 * Interfaz que define los casos de uso del dominio Prestamo
 */
public interface PrestamoUseCase {

    /**
     * Obtiene el detalle de un prestamo
     *
     * @param id
     * @return {@link Optional<Prestamo>}
     */
    Optional<Prestamo> obtenerPrestamoPorId(Long id);

    /**
     * Obtiene los prestamos que se han presentado ordenados por fecha de creacion
     *
     * @return {@link Collection<Prestamo>}
     */
    Collection<Prestamo> obtenerPrestamos();

    /**
     * Crea un prestamo
     * @param prestamo
     * @return {@link Long}
     */
    Long crearPrestamo(Prestamo prestamo);

    /**
     * Modificar estado de un prestamo
     * @param id
     * @param nuevoEstado
     * @return {@link Prestamo}
     */
    Prestamo modificarEstadoPrestamo(Long id, PrestamoEstado nuevoEstado);

    /**
     * Elimina un prestamo
     * @param idPrestamo
     * @return {@link boolean}
     */
    boolean eliminarPrestamo(Long idPrestamo);
}
