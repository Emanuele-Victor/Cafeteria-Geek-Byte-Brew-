package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente {

    private static final double TAXA_CONVERSAO_XP = 10.0;

    public ClienteVIP(String cpf, String nome) {
        super(cpf, nome);
    }

    @Override
    public void adicionarXP(double valorCompra) {
        this.saldoXP += valorCompra * 2.0;
    }

    public void pagarComXP(double valorTotal) throws PontosInsuficientesException {
        double custoEmPontos = valorTotal * TAXA_CONVERSAO_XP;

        if (this.saldoXP < custoEmPontos) {
            throw new PontosInsuficientesException("Saldo de XP insuficiente para resgate. Necessário: "
                    + custoEmPontos + " XP. Seu saldo atual: " + this.saldoXP + " XP.");
        }

        this.saldoXP -= custoEmPontos;
    }
}
