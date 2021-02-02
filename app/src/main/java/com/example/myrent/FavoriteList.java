package com.example.myrent;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FavoriteList extends AppCompatActivity {

    CardView findFiv;
    private Toolbar toolbar;
    DatabaseReference dRef;
    FavoriteClass favClass;
    FirebaseDatabase firebaseDatabase;
    LinearLayout nofav,favlayout;
    TextView renterid;
    ListView favList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    ArrayList<String> displayed;
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nofav = (LinearLayout) findViewById(R.id.no_fav_layout);
        favlayout = (LinearLayout) findViewById(R.id.fav_display_layout);
        displayed = new ArrayList<>();
        final String UsersId = getIntent().getStringExtra("userid");
        renterid = (TextView) findViewById(R.id.renter_id_holder);
        renterid.setText(UsersId);

        findFiv = (CardView) findViewById(R.id.favFind);
        findFiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoriteList.this,FindHome.class);
                startActivity(intent);
            }
        });

        favClass = new FavoriteClass();
        favList = (ListView) findViewById(R.id.fav_list);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dRef = firebaseDatabase.getReference("favorite");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.favorite_info,R.id.favorite_info,list);
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    favClass = ds.getValue(FavoriteClass.class);
                    String uid=favClass.getUserId();
                    String rid=renterid.getText().toString();
                    String hid = favClass.getHouseId();
                    if (uid.equals(rid)) {
                        list.add(favClass.getWhere().toString()+" \n" +favClass.getPrice().toString()+"\n");
                        count = count + 1;
                        displayed.add(hid);
                    }
                }
                favList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        favList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FavoriteList.this,HouseProfile.class);
                String HouseId = displayed.get(position);
                intent.putExtra("houseid",HouseId);
                intent.putExtra("renterid",UsersId);
                intent.putExtra("form",""+1);
                intent.putExtra("dis","No");
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
