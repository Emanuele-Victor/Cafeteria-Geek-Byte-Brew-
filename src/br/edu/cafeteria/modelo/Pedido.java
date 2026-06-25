package br.edu.cafeteria.modelo;

import java.util.ArrayList;
import java.util.List;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;

public class Pedido {

    private static int contadorSequencial = 1;

    private int id;
    private List<ItemPedido> itens;
    private Cliente cliente;
    private double desconto;

    public Pedido() {
        this.id = contadorSequencial++;
        this.itens = new ArrayList<>();
        this.desconto = 0;
    }

    public int getId() {
        return id;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public void adicionarItem(Produto p) throws EstoqueInsuficienteException {
        this.adicionarItem(p, 1);
    }

    public void adicionarItem(Produto p, int quantidade) throws EstoqueInsuficienteException {
        if (quantidade > p.getQuantidadeEstoque()) {
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente para o produto: " + p.getNome()
                + " (Disponível: " + p.getQuantidadeEstoque() + ")"
            );
        }

        ItemPedido novoItem = new ItemPedido(p, quantidade);
        this.itens.add(novoItem);

        p.setQuantidadeEstoque(p.getQuantidadeEstoque() - quantidade);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.calcularSubtotal();
        }
        return total;
    }

    public double getValorComDesconto() {
        double valorFinal = calcularTotal() - desconto;
        return valorFinal;
    }
}
