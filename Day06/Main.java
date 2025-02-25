package com.empresa.transportes;

import com.empresa.transportes.model.Veiculo;
import com.empresa.transportes.model.Motorista;
import com.empresa.transportes.model.Rota;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Criar um veículo
        Veiculo veiculo = new Veiculo();
        veiculo.setPlaca("ABC1234");
        veiculo.setModelo("Ônibus Urbano");
        veiculo.setAno(2020);
        veiculo.setTipo("Ônibus");

        // Criar um motorista
        Motorista motorista = new Motorista();
        motorista.setNome("João Silva");
        motorista.setCnh("12345678901");
        motorista.setDataNascimento(LocalDate.of(1990, 5, 15));

        // Criar uma rota
        Rota rota = new Rota();
        rota.setOrigem("São Paulo");
        rota.setDestino("Rio de Janeiro");
        rota.setDistanciaKm(430.5);

        // Imprimir informações
        System.out.println("Veículo: " + veiculo);
        System.out.println("Motorista: " + motorista);
        System.out.println("Rota: " + rota);
    }
}