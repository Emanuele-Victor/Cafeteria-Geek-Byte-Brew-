package br.edu.cafeteria.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    private static int contadorSequencial = 1;

    private int id;
    private List<ItemPedido> itens;

    public Pedido() {
        this.id = contadorSequencial++;
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void adicionarItem(Produto p) {
       this.adicionarItem(p, 1);
    }
 
    public void adicionarItem(Produto p, int quantidade) {        
        ItemPedido novoItem = new ItemPedido(p, quantidade);        
        this.itens.add(novoItem);
    }
}