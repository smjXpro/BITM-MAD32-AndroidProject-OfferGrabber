package pro.smjx.offergrabber;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "offer_db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_PRODUCT = "tbl_product";
    public static final String TBL_PRODUCT_COL_ID = "product_id";
    public static final String TBL_PRODUCT_COL_NAME = "product_name";
    public static final String TBL_PRODUCT_COL_CATEGORY = "product_category";
    public static final String TBL_PRODUCT_COL_BRAND = "product_brand";
    public static final String TBL_PRODUCT_COL_DESCRIPTION = "product_description";
    public static final String TBL_PRODUCT_COL_PRICE = "product_price";
    public static final String TBL_PRODUCT_COL_DISCOUNT_RATE = "product_discount_rate";
    public static final String TBL_PRODUCT_COL_DISCOUNTED_PRICE = "product_discounted_price";
    public static final String TBL_PRODUCT_COL_OFFER_DETAILS = "product_offer_details";


    public static final String CREATE_TABLE_PRODUCT = "CREATE TABLE "+TABLE_PRODUCT+"("+
            TBL_PRODUCT_COL_ID+" INTEGER PRIMARY KEY, "+
            TBL_PRODUCT_COL_NAME+" TEXT, "+
            TBL_PRODUCT_COL_CATEGORY+" TEXT, "+
            TBL_PRODUCT_COL_BRAND+" TEXT, "+
            TBL_PRODUCT_COL_DESCRIPTION+" TEXT, "+
            TBL_PRODUCT_COL_PRICE+" REAL, "+
            TBL_PRODUCT_COL_DISCOUNT_RATE+" INTEGER, "+
            TBL_PRODUCT_COL_DISCOUNTED_PRICE+" REAL, "+
            TBL_PRODUCT_COL_OFFER_DETAILS+" TEXT); ";



    public ProductDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

