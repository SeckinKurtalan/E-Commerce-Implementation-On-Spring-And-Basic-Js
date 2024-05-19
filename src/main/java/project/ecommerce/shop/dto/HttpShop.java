package project.ecommerce.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@AllArgsConstructor

public class HttpShop {

    public String productName;
    public String companyName;
    public Double price;

    @Override
    public String toString() {
        return "Shop{" +
                ", productName='" + productName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", price=" + price +
                '}';
    }
}
