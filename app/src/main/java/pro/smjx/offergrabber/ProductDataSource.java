package pro.smjx.offergrabber;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductDataSource {
    private ProductDatabaseHelper helper;
    private SQLiteDatabase db;

    public ProductDataSource(Context context) {
        helper = new ProductDatabaseHelper(context);
    }

    private void openDatabase() {
        db = helper.getWritableDatabase();
    }

    private void closeDatabase() {
        db.close();
    }

    public boolean insertNewProduct(Product product) {
        this.openDatabase();
        ContentValues values = new ContentValues();//maps data for table columns
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_NAME, product.getProductName());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_CATEGORY, product.getProductCategory());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_BRAND, product.getProductBrand());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_DESCRIPTION, product.getProductDescription());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_PRICE, product.getProductPrice());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNT_RATE, product.getProductDiscountRate());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNTED_PRICE, product.getProductDiscountedPrice());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_OFFER_DETAILS, product.getProductOfferDetails());

        long insertedRow = db.insert(ProductDatabaseHelper.TABLE_PRODUCT, null, values);
        this.closeDatabase();
        if (insertedRow > 0) {
            return true;
        }
        return false;
    }

    public List<Product> getAllProducts() {
        this.openDatabase();
        List<Product> products = new ArrayList<>();
        Cursor cursor = db.query(ProductDatabaseHelper.TABLE_PRODUCT, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int pid = cursor.getInt(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_NAME));
                String category = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_CATEGORY));
                String brand = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_BRAND));
                String description = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DESCRIPTION));
                double price = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_PRICE)));
                int discountRate = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNT_RATE)));
                double discountedPrice = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNTED_PRICE)));
                String offerDetails = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_OFFER_DETAILS));

                Product product = new Product(pid, name, category, brand, description,price,discountRate,discountedPrice,offerDetails);
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.closeDatabase();
        return products;
    }


    public List<Product> getBestDeals() {
        this.openDatabase();
        List<Product> products = new ArrayList<>();
       Cursor cursor = db.query(ProductDatabaseHelper.TABLE_PRODUCT, null, null, null, null, null, ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNT_RATE+" DESC");
        //Cursor cursor = db.rawQuery("SELECT * FROM "+ProductDatabaseHelper.TABLE_PRODUCT+" order by "+ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNT_RATE+" asc",new String[]{});
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                int pid = cursor.getInt(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_NAME));
                String category = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_CATEGORY));
                String brand = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_BRAND));
                String description = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DESCRIPTION));
                double price = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_PRICE)));
                int discountRate = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNT_RATE)));
                double discountedPrice = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNTED_PRICE)));
                String offerDetails = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_OFFER_DETAILS));

                Product product = new Product(pid, name, category, brand, description,price,discountRate,discountedPrice,offerDetails);
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.closeDatabase();
        return products;
    }


    public Product getProductById(int pid) {
        Product product = null;
        this.openDatabase();
        String pidString = String.valueOf(pid);
        String selection = ProductDatabaseHelper.TBL_PRODUCT_COL_ID + "=?";

        Cursor cursor = db.query(ProductDatabaseHelper.TABLE_PRODUCT, null, ProductDatabaseHelper.TBL_PRODUCT_COL_ID + " = " + pid, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_NAME));
            String category = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_CATEGORY));
            String brand = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_BRAND));
            String description = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DESCRIPTION));
            double price = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_PRICE)));
            int discountRate = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNT_RATE)));
            double discountedPrice = Double.parseDouble(cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNTED_PRICE)));
            String offerDetails = cursor.getString(cursor.getColumnIndex(ProductDatabaseHelper.TBL_PRODUCT_COL_OFFER_DETAILS));

            product = new Product(name, category, brand, description,price,discountRate,discountedPrice,offerDetails);
        }
        cursor.close();
        this.closeDatabase();
        return product;
    }

    public boolean deleteProduct(int pid) {
        this.openDatabase();
        int deletedRow = db.delete(ProductDatabaseHelper.TABLE_PRODUCT, ProductDatabaseHelper.TBL_PRODUCT_COL_ID + " = " + pid, null);
        this.closeDatabase();
        if (deletedRow > 0) {
            return true;
        }
        return false;
    }

    public boolean updateProduct(Product product) {
        this.openDatabase();
        ContentValues values = new ContentValues();
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_NAME, product.getProductName());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_CATEGORY, product.getProductCategory());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_BRAND, product.getProductBrand());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_DESCRIPTION, product.getProductDescription());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_PRICE, product.getProductPrice());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNT_RATE, product.getProductDiscountRate());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_DISCOUNTED_PRICE, product.getProductDiscountedPrice());
        values.put(ProductDatabaseHelper.TBL_PRODUCT_COL_OFFER_DETAILS, product.getProductOfferDetails());
        int updatedRow = db.update(ProductDatabaseHelper.TABLE_PRODUCT, values, ProductDatabaseHelper.TBL_PRODUCT_COL_ID + "=" + product.getProductID(), null);
        if (updatedRow > 0) {
            return true;
        }
        return false;
    }
}
