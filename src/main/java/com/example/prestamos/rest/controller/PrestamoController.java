package com.example.prestamos.rest.controller;

import com.example.prestamos.domain.Prestamo;
import com.example.prestamos.domain.enumerations.PrestamoEstado;
import com.example.prestamos.domain.usecase.PrestamoUseCase;
import com.example.prestamos.rest.beans.PrestamoRequest;
import com.example.prestamos.rest.beans.PrestamoResponse;
import com.example.prestamos.rest.mapper.PrestamoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/prestamos")
@RequiredArgsConstructor
public class PrestamoController {

    private final PrestamoUseCase prestamoUseCase;
    private final PrestamoMapper prestamoMapper;

    @PostMapping
    @PreAuthorize("hasRole('CLIENTE')")
    public ResponseEntity<PrestamoResponse> crearPrestamo(@Valid @RequestBody PrestamoRequest request) {
        Prestamo prestamo = prestamoMapper.toPrestamo(request);
        prestamo.setFechaCreacion(java.time.LocalDate.now());
        Long id = prestamoUseCase.crearPrestamo(prestamo);
        prestamo.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(prestamoMapper.toResponse(prestamo));
    }

    @GetMapping
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<Collection<PrestamoResponse>> obtenerPrestamos() {
        Collection<PrestamoResponse> response = prestamoUseCase.obtenerPrestamos()
                .stream()
                .map(prestamoMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<PrestamoResponse> obtenerPrestamoPorId(@PathVariable Long id) {
        return prestamoUseCase.obtenerPrestamoPorId(id)
                .map(prestamoMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<PrestamoResponse> modificarEstado(@PathVariable Long id, @RequestParam PrestamoEstado nuevoEstado) {
        Prestamo prestamoModificado = prestamoUseCase.modificarEstadoPrestamo(id, nuevoEstado);
        return ResponseEntity.ok(prestamoMapper.toResponse(prestamoModificado));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<Void> eliminarPrestamo(@PathVariable Long id) {
        prestamoUseCase.eliminarPrestamo(id);
        return ResponseEntity.noContent().build();
    }

}
