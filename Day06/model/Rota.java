package com.empresa.transportes.model;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class Rota {
    private Long id;

    @NotBlank(message = "Origem é obrigatória")
    private String origem;

    @NotBlank(message = "Destino é obrigatório")
    private String destino;

    @Positive(message = "Distância deve ser positiva")
    private double distanciaKm;

    private Status status;

    public Rota() {
        this.status = Status.ATIVO;
    }
}