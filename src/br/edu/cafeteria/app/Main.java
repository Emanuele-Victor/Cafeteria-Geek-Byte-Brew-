package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.Produto;
import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.ClienteStandard;
import br.edu.cafeteria.modelo.ClienteVIP;
import br.edu.cafeteria.modelo.Pedido;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    // REQUISITO: Listas estáticas para servir de banco de dados (Tarefa 14)
    public static List<Produto> cardapio = new ArrayList<>();
    public static List<Cliente> clientesCadastrados = new ArrayList<>();

    // --- INÍCIO DOS MÉTODOS DO DENILSON ---
    public static void cadastrarCliente(Scanner scanner) {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo de Cliente (1 - Standard, 2 - VIP): ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer

        if (tipo == 1) {
            clientesCadastrados.add(new ClienteStandard(cpf, nome));
            System.out.println("Cliente Standard cadastrado com sucesso!");
        } else if (tipo == 2) {
            clientesCadastrados.add(new ClienteVIP(cpf, nome));
            System.out.println("Cliente VIP cadastrado com sucesso!");
        } else {
            System.out.println("Opção inválida. Cadastro cancelado.");
        }
    }

    public static void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        if (clientesCadastrados.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado no sistema.");
            return;
        }
        for (Cliente c : clientesCadastrados) {
            String tipo = (c instanceof ClienteVIP) ? "VIP" : "Standard";
            System.out.println("CPF: " + c.getCpf() + " | Nome: " + c.getNome() + " | Categoria: " + tipo + " | Saldo XP: " + c.getSaldoXP());
        }
    }

    public static void novaVenda(Scanner scanner) {
        System.out.println("\n--- Nova Venda (Abertura de Pedido) ---");
        Pedido pedido = new Pedido(); 
        
        System.out.print("Deseja identificar o cliente? (S/N): ");
        String identificar = scanner.nextLine();
        
        if (identificar.equalsIgnoreCase("S")) {
            System.out.print("Digite o CPF do cliente: ");
            String cpfBusca = scanner.nextLine();
            for (Cliente c : clientesCadastrados) {
                if (c.getCpf().equals(cpfBusca)) {
                    pedido.setCliente(c);
                    System.out.println("Cliente " + c.getNome() + " vinculado à venda!");
                    break;
                }
            }
            if (pedido.getCliente() == null) {
                System.out.println("CPF não encontrado. A venda prosseguirá como cliente casual.");
            }
        }

        boolean adicionandoItens = true;
        while (adicionandoItens) {
            System.out.print("\nDigite o Código do Produto (ou 'F' para finalizar itens): ");
            String codigo = scanner.nextLine();
            
            if (codigo.equalsIgnoreCase("F")) {
                adicionandoItens = false;
                continue;
            }

            Produto produtoEncontrado = null;
            for (Produto p : cardapio) {
                if (p.getCodigo().equalsIgnoreCase(codigo)) {
                    produtoEncontrado = p;
                    break;
                }
            }

            if (produtoEncontrado != null) {
                System.out.print("Digite a quantidade desejada: ");
                int qtd = scanner.nextInt();
                scanner.nextLine(); 

                try {
                    pedido.adicionarItem(produtoEncontrado, qtd);
                    System.out.println("Item adicionado! Subtotal do pedido: R$ " + pedido.calcularTotal());
                } catch (EstoqueInsuficienteException e) {
                    System.out.println("❌ ERRO AO ADICIONAR ITEM: " + e.getMessage());
                }
            } else {
                System.out.println("Produto não encontrado no cardápio.");
            }
        }
        System.out.println("\nResumo: Pedido #" + pedido.getId() + " gerado. Total parcial: R$ " + pedido.calcularTotal());
    }
    // --- FIM DOS MÉTODOS DO DENILSON ---

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 7) { // Expandido para 7 opções
            System.out.println("\n--- MENU CAFETERIA (BYTE & BREW) ---");
            System.out.println("1. Cadastrar Produto (Manu)");
            System.out.println("2. Listar Produtos (Manu)");
            System.out.println("3. Remover Produto (Manu)");
            System.out.println("4. Cadastrar Novo Cliente (Denilson)");
            System.out.println("5. Listar Clientes (Denilson)");
            System.out.println("6. Abrir Nova Venda (Denilson)");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opcao: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.println("\n--- CADASTRAR PRODUTO ---");
                    System.out.println("1. Comida");
                    System.out.println("2. Bebida");
                    System.out.print("Escolha o tipo: ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Codigo: ");
                    String codigo = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preco Base: ");
                    double precoBase = scanner.nextDouble();
                    System.out.print("Quantidade em Estoque: ");
                    int estoque = scanner.nextInt();
                    scanner.nextLine();

                    if (tipo == 1) {
                        System.out.print("Tempo de Preparo (minutos): ");
                        int tempo = scanner.nextInt();
                        System.out.print("E vegano? (true/false): ");
                        boolean vegano = scanner.nextBoolean();
                        System.out.print("E sem gluten? (true/false): ");
                        boolean semGluten = scanner.nextBoolean();
                        scanner.nextLine();

                        cardapio.add(new Comida(codigo, nome, precoBase, estoque, tempo, vegano, semGluten));
                        System.out.println("Comida cadastrada com sucesso!");
                    } else if (tipo == 2) {
                        System.out.print("Tamanho (P, M, G): ");
                        String tamanho = scanner.nextLine();
                        System.out.print("Quantidade de Cafeina (mg): ");
                        int cafeina = scanner.nextInt();
                        scanner.nextLine();

                        cardapio.add(new Bebida(codigo, nome, precoBase, estoque, tamanho, cafeina));
                        System.out.println("Bebida cadastrada com sucesso!");
                    } else {
                        System.out.println("Tipo invalido!");
                    }
                    break;

                case 2:
                    System.out.println("\n--- LISTA DE PRODUTOS ---");
                    if (cardapio.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado.");
                    } else {
                        for (Produto p : cardapio) {
                            System.out.println("ID: " + p.getCodigo() + " | Nome: " + p.getNome() + " | Preco: R$ " + p.getPrecoBase() + " | Estoque: " + p.getQuantidadeEstoque());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- REMOVER PRODUTO ---");
                    if (cardapio.isEmpty()) {
                        System.out.println("Nenhum produto cadastrado para remover.");
                    } else {
                        System.out.print("Digite o codigo do produto a remover: ");
                        String codRemover = scanner.nextLine();
                        
                        boolean removido = false;
                        for (int i = 0; i < cardapio.size(); i++) {
                            if (cardapio.get(i).getCodigo().equals(codRemover)) {
                                cardapio.remove(i);
                                removido = true;
                                System.out.println("Produto removido com sucesso!");
                                break;
                            }
                        }
                        if (!removido) {
                            System.out.println("Produto nao encontrado.");
                        }
                    }
                    break;

                case 4:
                    cadastrarCliente(scanner);
                    break;

                case 5:
                    listarClientes();
                    break;

                case 6:
                    novaVenda(scanner);
                    break;

                case 7:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
        scanner.close();
    }
}