package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.Produto;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.ClienteStandard;
import br.edu.cafeteria.modelo.ClienteVIP;
import br.edu.cafeteria.modelo.Pedido;
import br.edu.cafeteria.modelo.ItemPedido;
import br.edu.cafeteria.excecao.PontosInsuficientesException;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Produto> listaProdutos = new ArrayList<>();
    private static List<Cliente> listaClientes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== BYTE & BREW - SISTEMA ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Cadastrar Cliente");
            System.out.println("4 - Criar e Simular Pedido");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto(scanner);
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    cadastrarCliente(scanner);
                    break;
                case 4:
                    simularFluxoPedido(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    public static void cadastrarProduto(Scanner scanner) {
        System.out.println("\n--- CADASTRAR PRODUTO ---");
        System.out.println("1 - Comida");
        System.out.println("2 - Bebida");
        System.out.print("Escolha o tipo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Preço Base: ");
        double preco = scanner.nextDouble();
        System.out.print("Estoque Inicial: ");
        int estoque = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
            System.out.print("Tempo de Preparo (min): ");
            int tempo = scanner.nextInt();
            System.out.print("É Vegano? (true/false): ");
            boolean vegano = scanner.nextBoolean();
            System.out.print("É Sem Glúten? (true/false): ");
            boolean gluten = scanner.nextBoolean();
            scanner.nextLine();

            listaProdutos.add(new Comida(codigo, nome, preco, estoque, tempo, vegano, gluten));
            System.out.println("Comida cadastrada com sucesso!");
        } else if (tipo == 2) {
            System.out.print("Tamanho (P/M/G): ");
            String tamanho = scanner.nextLine();
            System.out.print("Quantidade de Cafeína (mg): ");
            int cafeina = scanner.nextInt();
            scanner.nextLine();

            listaProdutos.add(new Bebida(codigo, nome, preco, estoque, tamanho, cafeina));
            System.out.println("Bebida cadastrada com sucesso!");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    public static void listarProdutos() {
        System.out.println("\n--- CARDÁPIO / PRODUTOS EM ESTOQUE ---");
        if (listaProdutos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        for (Produto p : listaProdutos) {
            System.out.println("[" + p.getCodigo() + "] " + p.getNome() + " - R$ " + p.getPrecoBase() + " (Estoque: " + p.getQuantidadeEstoque() + ")");
        }
    }

    public static void cadastrarCliente(Scanner scanner) {
        System.out.println("\n--- CADASTRAR CLIENTE ---");
        System.out.println("1 - Standard");
        System.out.println("2 - VIP");
        System.out.print("Escolha o tipo: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        if (tipo == 1) {
            listaClientes.add(new ClienteStandard(cpf, nome));
            System.out.println("Cliente Standard cadastrado!");
        } else if (tipo == 2) {
            listaClientes.add(new ClienteVIP(cpf, nome));
            System.out.println("Cliente VIP cadastrado!");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    public static void simularFluxoPedido(Scanner scanner) {
        if (listaProdutos.isEmpty()) {
            System.out.println("Cadastre produtos antes de simular uma venda.");
            return;
        }

        Pedido pedido = new Pedido();

        System.out.print("Informar CPF do cliente para fidelidade? (S/N): ");
        String associar = scanner.nextLine();
        if (associar.equalsIgnoreCase("S")) {
            System.out.print("Digite o CPF cadastrado: ");
            String cpf = scanner.nextLine();
            for (Cliente c : listaClientes) {
                if (c.getCpf().equals(cpf)) {
                    pedido.setCliente(c);
                    break;
                }
            }
            if (pedido.getCliente() == null) {
                System.out.println("Cliente não encontrado. Procedendo como Cliente Casual.");
            }
        }

        System.out.println("Adicionando o primeiro produto da lista por padrão para teste...");
        Produto p = listaProdutos.get(0);
        try {
            pedido.adicionarItem(p, 1);
            System.out.println("Adicionado: " + p.getNome());
        } catch (EstoqueInsuficienteException e) {
            System.out.println("Erro ao adicionar item: " + e.getMessage());
            return;
        }

        finalizarPedido(pedido, scanner);
    }

    public static void finalizarPedido(Pedido pedido, Scanner scanner) {
        System.out.print("É Dia de Evento Geek? (S/N): ");
        String respostaEvento = scanner.nextLine();
        
        double valorTotal = pedido.getValorTotal();

        if (respostaEvento.equalsIgnoreCase("S")) {
            double descontoBebidas = 0;
            for (ItemPedido item : pedido.getItens()) {
                Produto prod = item.getProduto();
                if (prod instanceof Bebida) {
                    descontoBebidas += (prod.getPrecoBase() * 0.10) * item.getQuantidade();
                }
            }
            valorTotal -= descontoBebidas;
            System.out.println("Desconto de Evento Geek aplicado. Valor deduzido: R$ " + descontoBebidas);
        }

        System.out.println("Valor total a pagar: R$ " + valorTotal);

        if (pedido.getCliente() != null) {
            Cliente cliente = pedido.getCliente();

            if (cliente instanceof ClienteVIP) {
                System.out.print("Deseja pagar com pontos de XP? (S/N): ");
                String pagarComXP = scanner.nextLine();

                if (pagarComXP.equalsIgnoreCase("S")) {
                    try {
                        ((ClienteVIP) cliente).pagarComXP(valorTotal);
                        System.out.println("Pago com saldo de XP com sucesso!");
                        System.out.println("Saldo restante de XP: " + cliente.getSaldoXP());
                        System.out.println("Pedido finalizado!");
                        return;
                    } catch (PontosInsuficientesException e) {
                        System.out.println("Erro: " + e.getMessage());
                        System.out.println("Direcionando para pagamento convencional.");
                    }
                }
            }
            
            cliente.adicionarXP(valorTotal);
            System.out.println("XP atualizado. Saldo de " + cliente.getNome() + ": " + cliente.getSaldoXP() + " XP");
        }

        System.out.println("Pedido finalizado com sucesso!");
    }
}