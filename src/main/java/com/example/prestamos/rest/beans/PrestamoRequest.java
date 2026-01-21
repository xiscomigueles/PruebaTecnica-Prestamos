package com.example.prestamos.rest.beans;

import com.example.prestamos.domain.enumerations.TipoDocumento;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoRequest {

    @NotBlank
    private String nombreSolicitante;

    @NotNull
    private TipoDocumento tipoDocumento;

    @NotBlank
    private String documentoIdentificativo;

    @NotNull
    private BigDecimal importe;

    @NotBlank
    private String divisa;

}
