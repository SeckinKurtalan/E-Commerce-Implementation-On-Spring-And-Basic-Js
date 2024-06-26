package project.ecommerce.shop.repositories;

import project.ecommerce.shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   Product findByName(String name);
   List<Product> findByPrice(Double price);
   List<Product> findByNameContaining(String name);
}