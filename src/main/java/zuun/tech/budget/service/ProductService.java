package zuun.tech.budget.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuun.tech.budget.domain.Product;
import zuun.tech.budget.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Guardar o actualizar un producto
    public Product save(Product product) {
        return productRepository.save(product);
    }

    // Obtener todos los productos
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    // Obtener un producto por ID
    public Product findById(Long id) {
        Optional<Product> result = productRepository.findById(id);
        return result.orElse(null); // Retorna el producto o null si no se encuentra
    }

    // Eliminar un producto por ID
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


}
