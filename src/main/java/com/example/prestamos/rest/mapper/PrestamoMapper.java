package com.example.prestamos.rest.mapper;

import com.example.prestamos.domain.Prestamo;
import com.example.prestamos.domain.vo.Divisa;
import com.example.prestamos.domain.vo.DocumentoIdentificativo;
import com.example.prestamos.domain.vo.Importe;
import com.example.prestamos.rest.beans.PrestamoRequest;
import com.example.prestamos.rest.beans.PrestamoResponse;
import org.springframework.stereotype.Component;

@Component
public class PrestamoMapper {


    public Prestamo toPrestamo(PrestamoRequest request) {
        Prestamo prestamo = new Prestamo();
        prestamo.setNombreSolicitante(request.getNombreSolicitante());
        prestamo.setDocumentoIdentificativo(new DocumentoIdentificativo(request.getTipoDocumento(), request.getDocumentoIdentificativo()));
        prestamo.setImporte(new Importe(request.getImporte(), new Divisa(request.getDivisa())));
        return prestamo;
    }

    public PrestamoResponse toResponse(Prestamo prestamo) {
        return PrestamoResponse.builder()
                .id(prestamo.getId())
                .nombreSolicitante(prestamo.getNombreSolicitante())
                .documentoIdentificativo(
                        prestamo.getDocumentoIdentificativo().getValor()
                )
                .importe(
                        prestamo.getImporte().getCantidad()
                )
                .divisa(
                        prestamo.getImporte().getDivisa().getCodigo()
                )
                .estado(prestamo.getEstado())
                .fechaCreacion(prestamo.getFechaCreacion())
                .build();
    }

}
