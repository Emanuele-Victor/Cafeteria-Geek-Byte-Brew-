package br.edu.cafeteria.servico;

import br.edu.cafeteria.modelo.Pedido;
import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.modelo.Bebida;

public class DescontoEventoGeek implements Promocional {

    private static final double PERCENTUAL_DESCONTO = 0.10;

    @Override
    public void aplicarDesconto(Pedido pedido) {
        double descontoTotal = 0;
        for (ItemPedido item : pedido.getItens()) {
            if (item.getProduto() instanceof Bebida) {
                descontoTotal += item.calcularSubtotal() * PERCENTUAL_DESCONTO;
            }
        }
        pedido.setDesconto(descontoTotal);
    }
}
