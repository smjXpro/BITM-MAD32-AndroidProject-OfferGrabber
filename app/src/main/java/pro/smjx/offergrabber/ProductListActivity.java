package pro.smjx.offergrabber;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {
    private ListView productLV;
    private Button addBtn;
    private List<Product> products = new ArrayList<>();
    private ProductAdapter adapter;
    private ProductDataSource dataSource;

    private boolean isLoggedIn=false;

    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        productLV = findViewById(R.id.productListView);
        addBtn = findViewById(R.id.addBtn);
        dataSource = new ProductDataSource(this);
        products = dataSource.getAllProducts();
        adapter = new ProductAdapter(this, products);
        productLV.setAdapter(adapter);
        registerForContextMenu(productLV);

        preference = new Preference(this);


        productLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product product = products.get(i);
                startActivity(new Intent(ProductListActivity.this,
                        ProductDetailsActivity.class)
                        .putExtra("id", product.getProductID()));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        isLoggedIn=preference.getLoginState();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        if (isLoggedIn==true) {
            getMenuInflater().inflate(R.menu.product_menu, menu);
            super.onCreateContextMenu(menu, v, menuInfo);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        int productId = products.get(position).getProductID();
        switch (item.getItemId()) {
            case R.id.edit:
                startActivity(new Intent(this, MainActivity.class)
                        .putExtra("id", productId));
                break;
            case R.id.delete:
                if (dataSource.deleteProduct(productId)) {
                    Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                    products.clear();
                    products = dataSource.getAllProducts();
                    adapter = new ProductAdapter(this, products);
                    productLV.setAdapter(adapter);
                } else {
                    Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void addProduct(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem loginItem = menu.findItem(R.id.itemLogin);
        MenuItem logoutItem = menu.findItem(R.id.itemLogout);

        if (isLoggedIn) {
            loginItem.setVisible(false);
            logoutItem.setVisible(true);

        } else {
            loginItem.setVisible(true);
            logoutItem.setVisible(false);

        }

        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.best_deals:
                startActivity(new Intent(this,BestDealsActivity.class));

                break;

            case R.id.itemLogin:
                Toast.makeText(this, "Logged in", Toast.LENGTH_SHORT).show();
                showLoginBox();
                break;

            case R.id.itemLogout:
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
                preference.setLoginState(false);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void showLoginBox() {


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);

        View view = inflater.inflate(R.layout.login_form, null);

        final EditText userNameET, passwordET;

        userNameET = view.findViewById(R.id.userNameET);

        passwordET = view.findViewById(R.id.passwordET);


        dialog.setTitle("Login Form");
        dialog.setIcon(R.mipmap.ic_launcher_round);
        dialog.setCancelable(false);

        dialog.setView(view);



        dialog.setPositiveButton("Login", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                User user = new User();
                String userName = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                if (userName.equals(user.userName) && password.equals(user.password)) {
                    Toast.makeText(ProductListActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    isLoggedIn = true;
                    addBtn.setVisibility(View.VISIBLE);
                    preference.setLoginState(true);



                } else {
                    Toast.makeText(ProductListActivity.this, "Username or Password is incorrect!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.setNegativeButton("Cancel", null);


        dialog.show();

    }






}
