package com.example.prestamos.domain.vo;

import com.example.prestamos.domain.enumerations.TipoDocumento;
import lombok.Getter;
import lombok.Setter;

/**
 * VO del documento identificativo del prestamo de la persona solicitante
 *
 * @author Fj migueles
 */
@Getter
@Setter
public class DocumentoIdentificativo {
    /** Tipo de documento identificativo de la persona */
    private TipoDocumento tipo;
    /** El valor del documento identificativo de la persona*/
    private String valor;


    /**
     * Constructor: Regla de negocio de la documentacion de la persona
     * @param tipo
     * @param valor
     */
    public DocumentoIdentificativo(TipoDocumento tipo, String valor) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de documento es obligatorio");
        }
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El valor del documento no puede estar vacío");
        }
        switch (tipo) {
            case DNI -> validarDni(valor);
            case NIE -> validarNie(valor);
            case PASAPORTE -> validarPasaporte(valor);
        }

        this.tipo = tipo;
        this.valor = valor;
    }

    /**
     * Validacion de DNI
     * @param valor
     */
    private void validarDni(String valor) {
        if (!valor.matches("\\d{8}[A-Z]")) {
            throw new IllegalArgumentException("Formato de DNI inválido");
        }
    }

    /**
     * Validacion de NIE
     * @param valor
     */
    private void validarNie(String valor) {
        if (!valor.matches("[XYZ]\\d{7}[A-Z]")) {
            throw new IllegalArgumentException("Formato de NIE inválido");
        }
    }

    /**
     * Validacion de pasaporte
     * @param valor
     */
    private void validarPasaporte(String valor) {
        if (valor.length() < 6) {
            throw new IllegalArgumentException("Formato de pasaporte inválido");
        }
    }

    @Override
    public String toString() {
        return tipo + ":" + valor;
    }

}
