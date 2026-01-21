package com.example.prestamos.application;

import com.example.prestamos.domain.Prestamo;
import com.example.prestamos.domain.enumerations.PrestamoEstado;
import com.example.prestamos.domain.repository.PrestamoRepository;
import com.example.prestamos.domain.usecase.PrestamoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class PrestamoUseCaseImpl implements PrestamoUseCase {

    private final PrestamoRepository prestamoRepository;

    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Optional<Prestamo> obtenerPrestamoPorId(Long id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public Collection<Prestamo> obtenerPrestamos() {
        List<Prestamo> prestamos = new ArrayList<>(prestamoRepository.findAll());
        prestamos.sort(Comparator.comparing(Prestamo:: getFechaCreacion));
        return prestamos;
    }

    @Override
    public Long crearPrestamo(Prestamo prestamo) {
        prestamo.setId(idGenerator.getAndIncrement());
        prestamo.setEstado(PrestamoEstado.PENDIENTE);
        prestamoRepository.save(prestamo);
        return prestamo.getId();
    }

    @Override
    public Prestamo modificarEstadoPrestamo(Long id, PrestamoEstado nuevoEstado) {
        Prestamo prestamo = prestamoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prestamo no encontrado"));
        prestamo.cambiarEstado(nuevoEstado);
        prestamoRepository.save(prestamo);
        return prestamo;
    }

    @Override
    public boolean eliminarPrestamo(Long idPrestamo) {
        Prestamo prestamo = prestamoRepository.findById(idPrestamo)
                .orElseThrow(() -> new IllegalArgumentException("Prestamo no encontrado"));
        if(prestamo.getEstado() != PrestamoEstado.CANCELADA){
            throw new IllegalStateException("Solo se pueden eliminar prestamos cancelados");
        }
        prestamoRepository.deleteById(idPrestamo);
        return true;
    }
}
