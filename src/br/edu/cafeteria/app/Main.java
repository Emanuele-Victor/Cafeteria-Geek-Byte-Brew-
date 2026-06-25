package br.edu.cafeteria.app;

import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Produto> cardapio = new ArrayList<>();
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n--- MENU CAFETERIA (CRUD PRODUTOS) ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Remover Produto");
            System.out.println("4. Sair");
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
