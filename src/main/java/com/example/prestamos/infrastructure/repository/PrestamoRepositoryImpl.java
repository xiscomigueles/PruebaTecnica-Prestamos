package com.example.prestamos.infrastructure.repository;

import com.example.prestamos.domain.Prestamo;
import com.example.prestamos.domain.repository.PrestamoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PrestamoRepositoryImpl implements PrestamoRepository {

    /**
     * Almacenamiento en Memoria
     */
    private final Map<Long, Prestamo> storage = new ConcurrentHashMap<>();

    @Override
    public Optional<Prestamo> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public Collection<Prestamo> findAll() {
        return storage.values();
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        storage.put(prestamo.getId(), prestamo);
        return prestamo;
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }
}
