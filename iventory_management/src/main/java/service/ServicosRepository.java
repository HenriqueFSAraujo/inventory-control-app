package service;

import java.util.List;
import java.util.Optional;

import model.Product;

public interface ServicosRepository {
		List<Product> findAll();
		List<Product> findProdutosByName(String nome);
		Product findById(Long id);
		Optional<Void> update (Product product);
	    public void save(Product product);
	    public void remove(Long id);

		   
}
