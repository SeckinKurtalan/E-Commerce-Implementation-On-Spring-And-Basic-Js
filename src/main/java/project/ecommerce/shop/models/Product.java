package project.ecommerce.shop.models;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "products")
@Data
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    private Double price;
    private Integer stock;
    @Column(columnDefinition = "Double default 0")
    private Double reduction;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    @Transient
    private boolean isReduction;

    public Product() {
    }

    public Product(String name, String description, Double price, Integer stock, Double reduction, String image) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.reduction = reduction;
        this.image = image;
    }


    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    public boolean isReduction() {
        return !this.reduction.equals(0D);
    }

    public void setReduction(boolean reduction) {
        isReduction = reduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return isReduction() == product.isReduction() && Objects.equals(getId(), product.getId()) && Objects.equals(getName(), product.getName()) && Objects.equals(getDescription(), product.getDescription()) && Objects.equals(getPrice(), product.getPrice()) && Objects.equals(getStock(), product.getStock()) && Objects.equals(isReduction(), product.isReduction());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getPrice(), getStock(), isReduction(), isReduction());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", reduction=" + reduction +
                ", isReduction=" + isReduction +
                '}';
    }
}