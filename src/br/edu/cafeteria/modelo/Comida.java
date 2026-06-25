package br.edu.cafeteria.modelo;

public class Comida extends Produto {
    private int tempoPreparoMinutos;
    private boolean isVegano;
    private boolean isSemGluten;

    public Comida(String codigo, String nome, double precoBase, int quantidadeEstoque, 
                  int tempoPreparoMinutos, boolean isVegano, boolean isSemGluten) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tempoPreparoMinutos = tempoPreparoMinutos;
        this.isVegano = isVegano;
        this.isSemGluten = isSemGluten;
    }

    public int getTempoPreparoMinutos() { return tempoPreparoMinutos; }
    public void setTempoPreparoMinutos(int tempoPreparoMinutos) { this.tempoPreparoMinutos = tempoPreparoMinutos; }

    public boolean isVegano() { return isVegano; }
    public void setVegano(boolean isVegano) { this.isVegano = isVegano; }

    public boolean isSemGluten() { return isSemGluten; }
    public void setSemGluten(boolean isSemGluten) { this.isSemGluten = isSemGluten; }
}