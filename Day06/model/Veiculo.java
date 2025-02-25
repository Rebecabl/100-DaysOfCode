package com.empresa.transportes.model;

import lombok.Data;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Veiculo {
    private Long id;

    @NotBlank(message = "Placa é obrigatória")
    @Size(min = 7, max = 8, message = "Placa deve ter 7 ou 8 caracteres")
    private String placa;

    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    private int ano;

    @NotBlank(message = "Tipo de veículo é obrigatório")
    private String tipo;

    private Status status;

    private LocalDate dataUltimaRevisao;

    public Veiculo() {
        this.status = Status.ATIVO;
        this.dataUltimaRevisao = LocalDate.now();
    }
}