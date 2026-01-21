package com.example.prestamos.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * VO con la informacion de la divisa
 *
 * @author Fj migueles
 */
@Getter
@Setter
public class Divisa {

    /** Codigo de la divisa */
    private String codigo;

    /**
     * Constructor: Regla de negocio que valida si el codigo de la divisa es correcto
     * @param codigo
     */
    public Divisa(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El codigo de divisa no puede estar vacio");
        }
        this.codigo = codigo.toUpperCase();
    }

    @Override
    public String toString() {
        return codigo;
    }
}
