package project.ecommerce.shop.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.ecommerce.account.models.AccountEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
@Data
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String sessionToken;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private Set<CartItem> items;
    @Transient
    private Double totalPrice;
    @Transient
    private int nbItems;
    @OneToOne(fetch = FetchType.EAGER,orphanRemoval = true)
    private AccountEntity user;
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    public ShoppingCart( ) {
       this.items = new HashSet<>();
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", sessionToken='" + sessionToken + '\'' +
                ", items=" + items +
                ", totalPrice=" + totalPrice +
                ", nbItems=" + nbItems +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}