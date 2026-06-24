package br.edu.cafeteria.app;

import java.util.ArrayList;
import java.util.List;

import br.edu.cafeteria.modelo.Produto;
import br.edu.cafeteria.modelo.Cliente;

public class Main {

    public static List<Produto> cardapio = new ArrayList<>();
    public static List<Cliente> clientesCadastrados = new ArrayList<>();

    public static void main(String[] args) {
        
        System.out.println("Bem-vindo ao Sistema da Cafeteria Geek 'Byte & Brew'!");
        System.out.println("Status: Sistema iniciado com o banco de dados limpo.");
        System.out.println("------------------------------------------------------");

    }
}