package project.ecommerce.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {
     private Long id;
    private  String name;
    private  String description;
    private  Double price;
    private  Integer stock;
    private  Double reduction;
    private  String image;
    private  MultipartFile imageFile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto entity = (ProductDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.price, entity.price) &&
                Objects.equals(this.stock, entity.stock) &&
                Objects.equals(this.reduction, entity.reduction) &&
                Objects.equals(this.image, entity.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, stock, reduction, image);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "price = " + price + ", " +
                "stock = " + stock + ", " +
                "reduction = " + reduction + ", " +
                "image = " + image + ")";
    }
}
