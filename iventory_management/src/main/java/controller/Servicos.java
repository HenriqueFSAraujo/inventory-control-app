package controller;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import model.Product;
import service.ServicosRepository;

public class Servicos implements ServicosRepository {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BancoPU");
	private static EntityManager entityManager = entityManagerFactory.createEntityManager();

	public List<Product> findAll() {
		// Consulta listando todos os produtos utilizando o Entity Manager
		return entityManager.createQuery("SELECT p FROM Produtos p", Product.class).getResultList();
	}

	// Buscar produtos pelo ID
	public Product findById(Long id) {
		Product produto = entityManager.find(Product.class, id);
		return Optional.ofNullable(produto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
	}

	
	public List<Product> findProdutosByName(String name) {
		Query query = entityManager.createQuery("SELECT p FROM Produtos p WHERE p.nome = :nome");
		query.setParameter("nome", name);
		return query.getResultList();
	}

	
	public void save(Product produto) {
		entityManager.getTransaction().begin();
		entityManager.persist(produto);
		entityManager.getTransaction().commit();
	}

	// Atualizar produtos na tabela
	
	public Optional<Void> update(Product produtos) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(produtos);
			entityManager.getTransaction().commit();
			return Optional.empty(); // Operação concluída com sucesso
		} catch (Exception e) {
			// Se ocorrer algum erro durante a alteração
			entityManager.getTransaction().rollback();
			return Optional.ofNullable(null).empty(); // Ou lançar uma exceção
		}
	}

	// Exclusão de produtos utilizando o método findByID para localizar o obejto a
	// ser excluido

	public void remove(Long id) {
		Product produtos = findById(id);
		if (produtos != null) {
			entityManager.getTransaction().begin();
			entityManager.remove(produtos);
			entityManager.getTransaction().commit();
		}	
	}
}