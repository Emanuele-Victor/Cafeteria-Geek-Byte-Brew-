package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente {

    public ClienteVIP(String cpf, String nome) {
        super(cpf, nome);
    }

    @Override
    public void adicionarXP(double valorCompra) {
        this.saldoXP += (valorCompra * 2.0);
    }

    public void pagarComXP(double valorTotal) throws PontosInsuficientesException {
        double custoEmPontos = valorTotal * Cliente.TAXA_CONVERSAO_XP_POR_REAL;
        
        if (this.saldoXP < custoEmPontos) {
            throw new PontosInsuficientesException("Saldo de XP insuficiente para resgate. Necessário: " 
                    + custoEmPontos + " XP. Seu saldo atual: " + this.saldoXP + " XP.");
        }
        
        this.saldoXP -= custoEmPontos;
    }
}