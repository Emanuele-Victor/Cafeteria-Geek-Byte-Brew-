package br.edu.cafeteria.modelo;

public class Bebida extends Produto {
    private String tamanho; // Ex: "P", "M", "G"
    private int quantidadeCafeinaMg;

    public Bebida(String codigo, String nome, double precoBase, int quantidadeEstoque, 
                  String tamanho, int quantidadeCafeinaMg) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tamanho = tamanho;
        this.quantidadeCafeinaMg = quantidadeCafeinaMg;
    }

    public String getTamanho() { return tamanho; }
    public int getQuantidadeCafeinaMg() { return quantidadeCafeinaMg; }
}