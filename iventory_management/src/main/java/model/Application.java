package model;

import java.util.List;

import controller.Servicos;
import service.DataBaseConnection;
import service.ServicosRepository;

public class Application {

	public static void main(String[] args) {
		DataBaseConnection.conectar();
		salvarProduto();
		alterarProduto();
		excluirProduto();
		listarProdutos();
	}
	
	static void salvarProduto() {
		Product produtos = new Product();
		produtos.setId((long) 1);
		produtos.setNomeProduto("PÃO DE FORMA");
		produtos.setDescricao("Produto");
		produtos.setEstoque(2);
		produtos.setPreco(10.0);
		ServicosRepository servico = new Servicos();
		servico.save(produtos);
		
	}
	
	static void alterarProduto() {
		ServicosRepository servico = new Servicos();
		Product produtos = servico.findById((long) 1);
		produtos.setId((long) 5);
		produtos.setNomeProduto("FERMENTO QUÍMICO");
		produtos.setDescricao("Produto para bolos");
		produtos.setEstoque(10);
		produtos.setPreco(5.99);
		servico.update(produtos);
	}
	
	static void excluirProduto() {
		ServicosRepository servico = new Servicos();
		Product produtos = servico.findById((long) 5);
		servico.remove((long)5);
	}
	
	static void listarProdutos() {
		ServicosRepository servico = new Servicos();
		List<Product> produtos = servico.findAll();
		if (!produtos.isEmpty()) {
            System.out.println("Lista de Produtos:");
            for (Product produto : produtos) {
                System.out.println("ID: " + produto.getId());
                System.out.println("Nome: " + produto.getNomeProduto());
                System.out.println("Preço: " + produto.getPreco());
                System.out.println("Estoque: " + produto.getEstoque());
                System.out.println("Descrição: " + produto.getDescricao());
                System.out.println();
            }
        } else {
            System.out.println("Nenhum produto encontrado.");
        }
	}
}
