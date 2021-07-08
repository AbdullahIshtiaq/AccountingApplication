package com.example.accountingapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.accountingapplication.viewModel.OrderViewModel;
import com.example.accountingapplication.R;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewHome;
    private ImageView imageViewDetails;
    private static RelativeLayout mRelativeLayout;
    private RelativeLayout mRelativeLayout1;
    private Button mSortButton;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton;
    private HomeFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewHome = findViewById(R.id.image_view_home);
        imageViewDetails = findViewById(R.id.image_view_details);
        mRelativeLayout = findViewById(R.id.relativeLayout_in_main);
        mRelativeLayout1 = findViewById(R.id.progressbarLayout);
        mSortButton = findViewById(R.id.button_sort);
        mRadioGroup = findViewById(R.id.radioGroup_sortingOptions);

        HomeFragment homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, homeFragment).commit();
        currentFragment = homeFragment;

        imageViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                currentFragment = homeFragment;
            }
        });

        imageViewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsFragment detailsFragment = new DetailsFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, detailsFragment).commit();
            }
        });

        mSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = mRadioGroup.getCheckedRadioButtonId();
                mRadioButton = (RadioButton) findViewById(selectedId);
                mRelativeLayout1.setVisibility(View.VISIBLE);
                if (mRadioButton.getId() == R.id.radio_button_orderNo_DECS) {
                    currentFragment.sortedByOrderNumberDecs();
                    mRelativeLayout1.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.GONE);
                    return;
                }
                if (mRadioButton.getId() == R.id.radio_button_orderNo_ACS) {
                    currentFragment.sortedByOrderNumberAcs();
                    mRelativeLayout1.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.GONE);
                    return;
                }
                if (mRadioButton.getId() == R.id.radio_button_date_DECS) {
                    currentFragment.sortedByDateDecs();
                    mRelativeLayout1.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.GONE);
                    return;
                }
                if (mRadioButton.getId() == R.id.radio_button_date_ACS) {
                    currentFragment.sortedByDateAcs();
                    mRelativeLayout1.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.GONE);
                    return;
                }
                if (mRadioButton.getId() == R.id.radio_button_price_DECS) {
                    currentFragment.sortedByPriceDecs();
                    mRelativeLayout1.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.GONE);
                    return;
                }
                if (mRadioButton.getId() == R.id.radio_button_price_ACS) {
                    currentFragment.sortedByPriceAcs();
                    mRelativeLayout1.setVisibility(View.GONE);
                    mRelativeLayout.setVisibility(View.GONE);
                    return;
                }
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.sort_order_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == R.id.sort_orders) {
//            mRelativeLayout.setVisibility(View.VISIBLE);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    public void dummyClick(View view) {
    }
    public static void sort(){
        mRelativeLayout.setVisibility(View.VISIBLE);
    }

}