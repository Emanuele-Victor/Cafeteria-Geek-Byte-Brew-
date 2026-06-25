package br.edu.cafeteria.modelo;

public abstract class Cliente {
    private String cpf;
    private String nome;
    protected double saldoXP;

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
        this.saldoXP = 0; // 
    }

    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public double getSaldoXP() { return saldoXP; }
    
    public abstract void adicionarXP(double valorCompra);
}