package project.ecommerce.shop.servicies;

import project.ecommerce.account.models.AccountEntity;
import project.ecommerce.account.models.AppUser;
import project.ecommerce.account.repositories.AccountRepository;
import project.ecommerce.shop.models.CartItem;
import project.ecommerce.shop.models.ShoppingCart;
import project.ecommerce.shop.repositories.CartItemRepository;
import project.ecommerce.shop.repositories.ProductRepository;
import project.ecommerce.shop.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final AccountRepository accountRepository;
    @Autowired
    public ShopService(CartItemRepository cartItemRepository, ProductRepository productRepository,
                       ShoppingCartRepository shoppingCartRepository, AccountRepository accountRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.accountRepository = accountRepository;
    }

    public void addItem(AppUser user, Long product_id, Integer quantity){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        AccountEntity account = accountRepository.findByEmail(email);
        ShoppingCart shoppingCart = shoppingCartRepository.findByUser(account);
        if(shoppingCart != null ){
            if(itemIsnotThere(shoppingCart.getItems(),product_id)){
                CartItem item = new CartItem(productRepository.getById(product_id),quantity);
                shoppingCart.getItems().add(item);
                shoppingCartRepository.save(shoppingCart);
            }else{

                for (CartItem it : shoppingCart.getItems()) {
                 if(it.getProduct().getId().equals(product_id)){
                     it.setQuantity(it.getQuantity()+quantity);
                     cartItemRepository.save(it);
                 }
                }
            }
        }else{
            ShoppingCart shopping = new ShoppingCart();
            shopping.setUser(account);
            CartItem item = new CartItem(productRepository.getById(product_id),quantity);
            cartItemRepository.save(item);
            shopping.getItems().add(item);
            shoppingCartRepository.save(shopping);

        }


    }

    private boolean itemIsnotThere(Set<CartItem> items,Long product_id) {
        for (CartItem item : items){
            if(item.getProduct().getId().equals(product_id)){
                return false;
            }
        }
        return true;
    }

    public ShoppingCart removeItem(AppUser user, Long item_id){
        ShoppingCart shoppingCart = getShoppingCartByUser(user);
        Set<CartItem> items = shoppingCart.getItems().stream().filter(item -> !item.getId().equals(item_id) ).collect(Collectors.toSet());
        shoppingCart.setItems(items);
        cartItemRepository.deleteById(item_id);
        return shoppingCartRepository.save(shoppingCart);
    }
    public ShoppingCart updateItemQuantity(AppUser user, Long item_id,Integer newQuantity){
        ShoppingCart shoppingCart = getShoppingCartByUser(user);
        for (CartItem item : shoppingCart.getItems()) {
            if(item.getId().equals(item_id)){
                item.setQuantity(newQuantity);
                cartItemRepository.save(item);
                break;
            }
        }
        return getShoppingCartByUser(user);
    }
    public void deleteShoppingCart(AppUser user){
        ShoppingCart shoppingCart = getShoppingCartByUser(user);
        shoppingCartRepository.delete(shoppingCart);
    }
    public ShoppingCart getShoppingCartByUser(AppUser user){
        AccountEntity acount = accountRepository.findByEmail(user.getUsername());
        return  shoppingCartRepository.findByUser(acount);
    }

    public ShoppingCart getShoppingCartById(Long id) {
        return  shoppingCartRepository.findById(id).get();
    }

    public ShoppingCart creatEmptyCart(AppUser principal) {
        AccountEntity acount = accountRepository.findByEmail(principal.getUsername());
        if(shoppingCartRepository.findByUser(acount) == null){
            ShoppingCart shope = new ShoppingCart();
            shope.setUser(acount);
            return shoppingCartRepository.save(shope);
        }
        else{
            return shoppingCartRepository.findByUser(acount);
        }

    }
}
