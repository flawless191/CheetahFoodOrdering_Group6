package com.example.cheetahfoodordering.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.cheetahfoodordering.entity.Category;

@Entity(tableName = "itemproducts", foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "category_id", childColumns = "category_id"))
public class ItemProduct {
    @PrimaryKey(autoGenerate = true)
    private int product_id;

    @ColumnInfo(name = "product_name")
    private String product_name;
    @ColumnInfo(name = "product_image")
    private int product_image;

    @ColumnInfo(name = "product_price")
    private float product_price;
    @ColumnInfo(name = "product_description")
    private String product_description;
    @ColumnInfo(name = "quantity")
    private int quantity;
    @ColumnInfo(name = "category_id")

    public int category_id;


    public ItemProduct(String product_name, int product_image, float product_price, String product_description, int quantity, int category_id) {
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_price = product_price;
        this.product_description = product_description;
        this.quantity = quantity;
        this.category_id = category_id;
    }

    public int getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public float getProduct_price() {
        return product_price;
    }

    public void setProduct_price(float product_price) {
        this.product_price = product_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemProduct{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_image=" + product_image +
                ", product_price=" + product_price +
                ", product_description='" + product_description + '\'' +
                ", quantity=" + quantity +
                ", category_id=" + category_id +
                '}';
    }
}
