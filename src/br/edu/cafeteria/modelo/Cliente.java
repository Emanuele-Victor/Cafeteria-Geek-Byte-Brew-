package br.edu.cafeteria.modelo;

public abstract class Cliente {

    private String nome;
    private String cpf;
    protected double saldoXP; 
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.saldoXP = 0.0; 
    }

    public abstract void adicionarXP(double valorGasto);
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public double getSaldoXP() { return saldoXP; }
}