package br.edu.cafeteria.modelo;

public class ClientePadrao extends Cliente {

    public ClientePadrao(String nome, String cpf, double saldoXP) {
        super(nome, cpf, saldoXP);
    }

    @Override
    public double calcularXPGanhado(double valorGasto) {
        return valorGasto * 1.0; 
    }
}