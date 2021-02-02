package com.example.myrent;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyHome extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    CardView find,fav;
    String Uid, user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);

        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        find = (CardView) findViewById(R.id.find_home_btn);
        fav = (CardView) findViewById(R.id.see_fav_btn);

        drawerLayout = findViewById(R.id.drawer_layaout);
        navigationView = findViewById(R.id.navigationView);

        /*            COMMUNICATING WITH OTHER CLASSES           */

        final String UsersId = getIntent().getStringExtra("userid");
        final String Class = getIntent().getStringExtra("class");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {

                    case R.id.find:
                        Intent intent = new Intent(MyHome.this,FindHome.class);
                        intent.putExtra("userid",UsersId);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.fav:
                        Intent intent2 = new Intent(MyHome.this,FavoriteList.class);
                        intent2.putExtra("userid",UsersId);
                        startActivity(intent2);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.edit:
                        Intent intent3 = new Intent(MyHome.this,ViewProfile.class);
                        intent3.putExtra("userid",UsersId);
                        intent3.putExtra("class",Class);
                        startActivity(intent3);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyHome.this,FindHome.class);
                intent.putExtra("userid",UsersId);
                startActivity(intent);
            }
        });

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MyHome.this,FavoriteList.class);
                intent2.putExtra("userid",UsersId);
                startActivity(intent2);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.logout:
                Intent intent2 = new Intent(MyHome.this,MainActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu st) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,st);

        return super.onCreateOptionsMenu(st);
    }
}
