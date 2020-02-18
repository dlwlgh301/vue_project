// 디비 셋팅 주석 
package com.web.curation.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private int productNumber;
    private String productName;
    private String link;
    private String image;
    private int price;

    public Product(String productName, String link, String image, int price) {
        this.productName = productName;
        this.link = link;
        this.image = image;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [image=" + image + ", link=" + link + ", price=" + price + ", productName=" + productName
                + ", productNumber=" + productNumber + "]";
    }

}
