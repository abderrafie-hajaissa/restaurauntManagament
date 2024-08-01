/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restrauntsysteme;

import java.sql.Date;

/**
 *
 * @author Abderrafie
 */
public class productData {

    private Integer id;
    private String productId;
    private String productName;
    private String type;
    private Integer stock;
    private Double price;
    private String statue;
    private String image;
    private Integer quantity;
    private Date date;

    public productData(Integer id, String productId, String ProductName,
            String type, Integer stock, Double price, String statue,
            String image, Date date) {

        this.id = id;
        this.productId = productId;
        this.productName = ProductName;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.statue = statue;
        this.image = image;
        this.date = date;

    }

    public productData(Integer id, String productId, String productName,
            String type, Integer quantity, Double price, String image, Date date) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.date = date;

    }

    public Integer getId() {
        return id;

    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {

        return productName;
    }

    public String getType() {

        return type;
    }

    public Integer getStock() {
        return stock;
    }

    public Double getPrice() {

        return price;
    }

    public String getStatue() {
        return statue;
    }

    public String getImage() {

        return image;

    }

    public Integer getQuantity() {

        return quantity;
    }

    public Date getDate() {
        return date;
    }
}
