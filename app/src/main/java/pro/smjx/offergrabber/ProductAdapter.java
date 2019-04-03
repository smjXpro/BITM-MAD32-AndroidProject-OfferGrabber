package pro.smjx.offergrabber;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> products;

    public ProductAdapter(@NonNull Context context, List<Product> products) {
        super(context, R.layout.product_row, products);
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.product_row, parent, false);
        TextView nameTV = convertView.findViewById(R.id.rowProductName);
        TextView brandTV = convertView.findViewById(R.id.rowProductBrand);
        TextView priceTV = convertView.findViewById(R.id.rowProductPrice);
        TextView discountTV = convertView.findViewById(R.id.rowProductDiscount);
        TextView discountedPriceTV = convertView.findViewById(R.id.rowProductDiscountedPrice);

        nameTV.setText("Product Name: "+products.get(position).getProductName());
        brandTV.setText("Brand: "+products.get(position).getProductBrand());
        priceTV.setText("Price: "+String.valueOf(products.get(position).getProductPrice())+" BDT");
        discountTV.setText("Discount: "+String.valueOf(products.get(position).getProductDiscountRate())+"%");
        discountedPriceTV.setText("Discounted Price: "+String.valueOf(products.get(position).getProductDiscountedPrice())+" BDT");
        return convertView;
    }
}
