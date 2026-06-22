package br.edu.cafeteria.modelo;

public class ClienteVIP extends Cliente {
   
    public static final double TAXA_CONVERSAO_XP = 10.0;

    public ClienteVIP(String nome, String cpf, double saldoXP) {
        super(nome, cpf, saldoXP);
    }

    @Override
    public double calcularXPGanhado(double valorGasto) {
        return valorGasto * 2.0; 
    }
}