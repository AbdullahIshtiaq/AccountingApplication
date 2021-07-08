package com.example.accountingapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.accountingapplication.R;

public class AddEditOrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_ORDER_NUMBER = "EXTRA_ORDER_NUMBER";
    public static final String EXTRA_DELIVERY = "EXTRA_DELIVERY";
    public static final String EXTRA_PRICE = "EXTRA_PRICE";
    public static final String EXTRA_PAYMENT_METHOD = "EXTRA_PAYMENT_METHOD";
    public static final String EXTRA_ORDER_THROUGH = "EXTRA_ORDER_THROUGH";
    public static final String EXTRA_DATE_TIME = "EXTRA_DATE_TIME";

    private EditText editTextOrderNumber;
    private EditText editTextPrice;
    private Spinner spinnerPaymentMethod;
    private Spinner spinnerDelivery;
    private Spinner spinnerOrderThrough;
    private static final String[] payment_method_option = {"Cash", "Card"};
    private static final String[] delivery_option = {"Delivery", "Takeaway"};
    private static final String[] orderThrough_option = {"None", "Delivero", "Uber Eats", "Just Eats"};
    private String paymentMethod = null;
    private String delivery = null;
    private String orderThrough = null;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        editTextOrderNumber = findViewById(R.id.edit_text_order_number);
        editTextPrice = findViewById(R.id.edit_text_price);
        spinnerPaymentMethod = findViewById(R.id.spinner_payment_method);
        spinnerDelivery = findViewById(R.id.spinner_delivery);
        spinnerOrderThrough = findViewById(R.id.spinner_orderThrough);

        ArrayAdapter<String> payment_method_adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, payment_method_option);
        payment_method_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaymentMethod.setAdapter(payment_method_adapter);
        spinnerPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                paymentMethod = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> delivery_adapter = new ArrayAdapter<String>
                (getBaseContext(), android.R.layout.simple_spinner_item, delivery_option);
        delivery_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDelivery.setAdapter(delivery_adapter);
        spinnerDelivery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                delivery = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<String> orderThrough_adapter = new ArrayAdapter<String>
                (getBaseContext(), android.R.layout.simple_spinner_item, orderThrough_option);
        orderThrough_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderThrough.setAdapter(orderThrough_adapter);
        spinnerOrderThrough.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                orderThrough = adapterView.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mToolbar = (Toolbar) findViewById(R.id.addOrder_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            getSupportActionBar().setTitle("Edit Note");
            editTextOrderNumber.setText(String.valueOf(intent.getIntExtra(EXTRA_ORDER_NUMBER, -1)));
            editTextPrice.setText(String.valueOf(intent.getIntExtra(EXTRA_PRICE, 0)));
        } else {
            getSupportActionBar().setTitle("Add Note");
        }
    }

    private void saveNote() {
        if (editTextOrderNumber.getText().toString().trim().isEmpty() || delivery.trim().isEmpty()
                || editTextPrice.getText().toString().trim().isEmpty() || paymentMethod.trim().isEmpty()
                || orderThrough.trim().isEmpty()) {
            Toast.makeText(AddEditOrderActivity.this, "Please fill every block", Toast.LENGTH_SHORT).show();
            return;
        }

        int order_number = Integer.parseInt(editTextOrderNumber.getText().toString());
        int price = Integer.parseInt(editTextPrice.getText().toString());

        Intent intent = new Intent();
        intent.putExtra("EXTRA_ORDER_NUMBER", order_number);
        intent.putExtra("EXTRA_PRICE", price);
        intent.putExtra("EXTRA_DELIVERY", delivery);
        intent.putExtra("EXTRA_PAYMENT_METHOD", paymentMethod);
        intent.putExtra("EXTRA_ORDER_THROUGH", orderThrough);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            String date_time = getIntent().getStringExtra(EXTRA_DATE_TIME);
            intent.putExtra("EXTRA_ID", id);
            intent.putExtra("EXTRA_DATE_TIME", date_time);
        }

        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_order:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}