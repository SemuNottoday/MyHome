package com.example.myrent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class HouseGallary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_gallary);

        String houseID = getIntent().getStringExtra("houseid");
        Toast.makeText(getApplicationContext(),""+houseID,Toast.LENGTH_LONG).show();
    }
}
