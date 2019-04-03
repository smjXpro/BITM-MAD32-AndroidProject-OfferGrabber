package pro.smjx.offergrabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ProductDetailsActivity extends AppCompatActivity {

    private ProductDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Product product;
        setContentView(R.layout.activity_product_details);
        int id = getIntent().getIntExtra("id",0);
        dataSource = new ProductDataSource(this);
        product = dataSource.getProductById(id);

        if(product != null){
            ((TextView)findViewById(R.id.showProductName)).setText("Product Name: "+product.getProductName());
            ((TextView)findViewById(R.id.showProductCategory)).setText("Category: "+product.getProductCategory());
            ((TextView)findViewById(R.id.showProductBrand)).setText("Brand: "+product.getProductBrand());
            ((TextView)findViewById(R.id.showProductDescription)).setText("Description: "+product.getProductDescription());
            ((TextView)findViewById(R.id.showProductPrice)).setText("Price: "+String.valueOf(product.getProductPrice())+" BDT");
            ((TextView)findViewById(R.id.showDiscountRate)).setText("Discount: "+String.valueOf(product.getProductDiscountRate())+"%");
            ((TextView)findViewById(R.id.showDiscountedPrice)).setText("Discounted Price: "+String.valueOf(product.getProductDiscountedPrice())+" BDT");
            ((TextView)findViewById(R.id.showOfferDetails)).setText("Offer Details: "+product.getProductOfferDetails());
        }
    }


}
