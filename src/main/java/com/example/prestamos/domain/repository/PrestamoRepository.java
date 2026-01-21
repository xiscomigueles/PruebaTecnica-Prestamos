package com.example.prestamos.domain.repository;

import com.example.prestamos.domain.Prestamo;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

/**
 * Interfaz que define el repositorio para el dominio Prestamo
 *
 * @author Fj migueles
 */
public interface PrestamoRepository {

    Optional<Prestamo> findById(Long id);
    Collection<Prestamo> findAll();
    Prestamo save(Prestamo prestamo);
    void deleteById(Long id);
}
