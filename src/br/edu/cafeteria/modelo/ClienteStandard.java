package br.edu.cafeteria.modelo;

public class ClienteStandard extends Cliente {

    public ClienteStandard(String nome, String cpf, double saldoXP) {
        super(nome, cpf, saldoXP);
    }

    @Override
    public double calcularXPGanhado(double valorGasto) {
        return valorGasto * 1.0; 
    }
}