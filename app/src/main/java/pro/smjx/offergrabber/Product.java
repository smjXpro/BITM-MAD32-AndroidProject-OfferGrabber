package pro.smjx.offergrabber;

import java.io.Serializable;

public class Product implements Serializable {
    private int productID;
    private String productName;
    private String productCategory;
    private String productBrand;
    private String productDescription;
    private double productPrice;
    private int productDiscountRate;
    private double productDiscountedPrice;
    private String productOfferDetails;


    public Product(int productID, String productName, String productCategory, String productBrand, String productDescription, double productPrice, int productDiscountRate, double productDiscountedPrice, String productOfferDetails) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productDiscountRate = productDiscountRate;
        this.productDiscountedPrice = productDiscountedPrice;
        this.productOfferDetails = productOfferDetails;
    }

    public Product(String productName, String productCategory, String productBrand, String productDescription, double productPrice, int productDiscountRate, double productDiscountedPrice, String productOfferDetails) {
        this.productName = productName;
        this.productCategory = productCategory;
        this.productBrand = productBrand;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productDiscountRate = productDiscountRate;
        this.productDiscountedPrice = productDiscountedPrice;
        this.productOfferDetails = productOfferDetails;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductDiscountRate() {
        return productDiscountRate;
    }

    public double getProductDiscountedPrice() {
        productDiscountedPrice = (productPrice-(productPrice*productDiscountRate)/100);
        return productDiscountedPrice;
    }

    public String getProductOfferDetails() {
        return productOfferDetails;
    }
}
