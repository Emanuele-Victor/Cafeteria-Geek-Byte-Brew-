package br.edu.cafeteria.modelo;

public class ClienteStandard extends Cliente {

    public ClienteStandard(String cpf, String nome) {
        super(cpf, nome);
    }

    @Override
    public void adicionarXP(double valorCompra) {
        double xpGanho = valorCompra * 1.0;
        
        this.saldoXP += xpGanho;
    }
}