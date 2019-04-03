package pro.smjx.offergrabber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText productNameET, productCategoryET, productBrandET, productDescriptionET, productPriceET, productDiscountRateET, productOfferDetailsET;
    private Button saveBtn;
    ProductDataSource source;
    private int productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        source = new ProductDataSource(this);
        productNameET = findViewById(R.id.productNameET);
        productCategoryET = findViewById(R.id.productCategoryET);
        productBrandET = findViewById(R.id.productBrandET);
        productDescriptionET = findViewById(R.id.productDescriptionET);
        productPriceET = findViewById(R.id.productPriceET);
        productDiscountRateET = findViewById(R.id.productDiscountRateET);
        productOfferDetailsET = findViewById(R.id.productOfferDetailsET);

        saveBtn = findViewById(R.id.saveBtn);
        productID = getIntent().getIntExtra("id", 0);
        if (productID > 0) {
            saveBtn.setText("Update");
            Product product = source.getProductById(productID);
            productNameET.setText(product.getProductName());
            productCategoryET.setText(product.getProductCategory());
            productBrandET.setText(product.getProductBrand());
            productDescriptionET.setText(product.getProductDescription());
            productPriceET.setText(String.valueOf(product.getProductPrice()));
            productDiscountRateET.setText(String.valueOf(product.getProductDiscountRate()));
            productOfferDetailsET.setText(product.getProductOfferDetails());

        }
    }

    public void saveProduct(View view) {
        String name = productNameET.getText().toString();
        String category = productCategoryET.getText().toString();
        String brand = productBrandET.getText().toString();
        String description = productDescriptionET.getText().toString();
        double price = Double.parseDouble(productPriceET.getText().toString());
        int discountRate = Integer.parseInt(productDiscountRateET.getText().toString());
        double discountedPrice=(price-(price*discountRate)/100);
        String offerDetails = productOfferDetailsET.getText().toString();
        boolean status = false;
        if (productID > 0) {
            Product product = new Product(productID, name, category, brand, description, price, discountRate, discountedPrice, offerDetails);
            status = source.updateProduct(product);
        } else {
            Product product = new Product(name, category, brand, description, price, discountRate, discountedPrice, offerDetails);
            status = source.insertNewProduct(product);
        }
        if (status==true) {
            Toast.makeText(this, "Operation Successful!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, ProductListActivity.class));

        } else {
            Toast.makeText(this, "Operation Failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void showAllOffers(View view) {
        List<Product> productList = source.getAllProducts();
        startActivity(new Intent(this,ProductListActivity.class));
        for (Product p : productList) {
            Log.e("product", "ID: " + p.getProductID());
            Log.e("product", "Name: " + p.getProductName());
            Log.e("product", "Category: " + p.getProductCategory());
            Log.e("product", "Brand: " + p.getProductBrand());
            Log.e("product", "Description: " + p.getProductDescription());
            Log.e("product", "Price: " + p.getProductPrice());
            Log.e("product", "Discount: " + p.getProductDiscountRate());
            Log.e("product", "Discounted Price: " + p.getProductDiscountedPrice());
            Log.e("product", "Offer Details: " + p.getProductOfferDetails());
            Log.e("product", "--------------------------------");
        }
    }


    public void showSpecificProducts(View view) {
        Product p = source.getProductById(1);
        startActivity(new Intent(this,ProductDetailsActivity.class).putExtra("product",p));
        Log.e("product", "ID: " + p.getProductID());
        Log.e("product", "Name: " + p.getProductName());
        Log.e("product", "Category: " + p.getProductCategory());
        Log.e("product", "Brand: " + p.getProductBrand());
        Log.e("product", "Description: " + p.getProductDescription());
        Log.e("product", "Price: " + p.getProductPrice());
        Log.e("product", "Discount: " + p.getProductDiscountRate());
        Log.e("product", "Discounted Price: " + p.getProductDiscountedPrice());
        Log.e("product", "Offer Details: " + p.getProductOfferDetails());
    }
}
