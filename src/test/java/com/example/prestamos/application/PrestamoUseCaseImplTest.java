package com.example.prestamos.application;

import com.example.prestamos.domain.Prestamo;
import com.example.prestamos.domain.repository.PrestamoRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PrestamoUseCaseImplTest {
    
    @Test
    void obtenerPrestamoPorId() {
        PrestamoRepository repository = mock(PrestamoRepository.class);
        PrestamoUseCaseImpl useCase = new PrestamoUseCaseImpl(repository);

        Prestamo prestamo = mock(Prestamo.class);
        when(repository.findById(1L)).thenReturn(Optional.of(prestamo));

        Optional<Prestamo> resultado = useCase.obtenerPrestamoPorId(1L);

        assertThat(resultado).isPresent();
        verify(repository).findById(1L);
    }

    @Test
    void obtenerPrestamos() {
        PrestamoRepository repository = mock(PrestamoRepository.class);
        PrestamoUseCaseImpl useCase = new PrestamoUseCaseImpl(repository);

        when(repository.findAll()).thenReturn(List.of());

        assertThat(useCase.obtenerPrestamos()).isEmpty();
    }
}
