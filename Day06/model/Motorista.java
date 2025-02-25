package com.empresa.transportes.model;

import lombok.Data;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class Motorista {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "CNH é obrigatória")
    @Size(min = 11, max = 11, message = "CNH deve ter 11 dígitos")
    private String cnh;

    private LocalDate dataNascimento;

    private Status status;

    public Motorista() {
        this.status = Status.ATIVO;
    }
}