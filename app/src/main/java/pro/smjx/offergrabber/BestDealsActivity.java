package pro.smjx.offergrabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BestDealsActivity extends AppCompatActivity {
    private ListView productLV;
    private List<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    private ProductDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_deals);

        productLV = findViewById(R.id.productListView);
        dataSource = new ProductDataSource(this);
        products = dataSource.getBestDeals();
        adapter = new ProductAdapter(this, products);
        productLV.setAdapter(adapter);
    }
}
