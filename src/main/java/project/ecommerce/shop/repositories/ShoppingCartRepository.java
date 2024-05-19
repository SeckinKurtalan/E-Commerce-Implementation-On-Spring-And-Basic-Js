package project.ecommerce.shop.repositories;

import project.ecommerce.account.models.AccountEntity;
import project.ecommerce.shop.models.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    ShoppingCart findBySessionToken(String  sessionToken);
    ShoppingCart findByUser(AccountEntity user);
}