package cart.repository;

import java.util.List;
import java.util.Optional;

import cart.domain.Product;

public interface ProductRepository {
	Product save(Product product);

	void update(Product product);

	Optional<Product> findById(Long id);

	List<Product> findAll();

	void delete(Product product);

	void deleteAll();

}
