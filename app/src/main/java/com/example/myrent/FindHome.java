package com.example.myrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

public class FindHome extends AppCompatActivity {

    private Toolbar toolbar;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String RWhere,RBed,RChild,RLate,RPower,RWith,Rlat,Rlong;
    TextView FWhere,FBed,FChild,FLate,FPower,FWith,Fprice,Flat,Flog;
    int RPrice=0;
    TextView renterName;
    ListView rentList;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    OwnersProfile ownersProfile;
    double Rlatitude,Rlongtitude,Hlatitude,Hlongtitude;
    int count = 0;
    double dis = 0.00;
    ArrayList<String> distanceHouse;
    ArrayList<String> displayed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_home);
        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        /*        USED TO RETRIEVE THE USERS PREFERENCE FROM DATABASE          */

        final String UsersId = getIntent().getStringExtra("userid");
        renterName = (TextView) findViewById(R.id.renter_name);
        FWhere = (TextView) findViewById(R.id.fwhere);
        FBed = (TextView) findViewById(R.id.fbed);
        FChild = (TextView) findViewById(R.id.fchild);
        FPower = (TextView) findViewById(R.id.fpower);
        FLate = (TextView) findViewById(R.id.flate);
        FWith = (TextView) findViewById(R.id.fwith);
        Fprice = (TextView) findViewById(R.id.fprice);
        Flat = (TextView) findViewById(R.id.latitude_renter);
        Flog = (TextView) findViewById(R.id.longtitude_renter);

        displayed = new ArrayList<>();
        distanceHouse = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(UsersId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String rWhere = dataSnapshot.child("where").getValue().toString();
                int rPrice = Integer.parseInt(dataSnapshot.child("price").getValue().toString());
                String rBed = dataSnapshot.child("bed").getValue().toString();
                String rChild = dataSnapshot.child("children").getValue().toString();
                String rLate = dataSnapshot.child("late").getValue().toString();
                String rPower = dataSnapshot.child("power").getValue().toString();
                String rWith = dataSnapshot.child("with").getValue().toString();
                String RName = dataSnapshot.child("username").getValue().toString();
                String Rlat = dataSnapshot.child("latitude").getValue().toString();
                String Rlong = dataSnapshot.child("longtitude").getValue().toString();
                renterName.append(RName);
                FWhere.setText(rWhere);
                FBed.setText(rBed);
                FPower.setText(rPower);
                FLate.setText(rLate);
                FWith.setText(rWith);
                Fprice.setText(""+rPrice);
                FChild.setText(rChild);
                Flat.setText(Rlat);
                Flog.setText(Rlong);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*                  TO FIND AND DISPLAY THE RENT HOUSE THAT MUCH THE USERS REQUIRMENT       */

        ownersProfile = new OwnersProfile();
        rentList = (ListView) findViewById(R.id.rent_list);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("owners");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.house_info,R.id.house_info,list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    int rate = 0;
                    RWhere = FWhere.getText().toString();
                    RPrice = Integer.parseInt(Fprice.getText().toString());
                    RBed = FBed.getText().toString();
                    RChild = FChild.getText().toString();
                    RPower = FPower.getText().toString();
                    RLate = FLate.getText().toString();
                    RWith = FWith.getText().toString();
                    double Rlat = Double.parseDouble(Flat.getText().toString());
                    double Rlong = Double.parseDouble(Flog.getText().toString());

                    ownersProfile = ds.getValue(OwnersProfile.class);
                    String Where = ownersProfile.getWhere().toString();
                    String Id = ownersProfile.getOid().toString();
                    int Price = ownersProfile.getPrice();
                    String Bed = ownersProfile.getBed();
                    String Child = ownersProfile.getChildren();
                    String Power = ownersProfile.getPower();
                    String Late = ownersProfile.getLate();
                    String With = ownersProfile.getLive();
                    double Hlat = Double.parseDouble(ownersProfile.getLatitude());
                    double Hlong = Double.parseDouble(ownersProfile.getLongtitude());

                    dis = distance(Rlat,Rlong,Hlat,Hlong);
                    if (RWhere.equals("Near"))
                    {
                        if(dis < 1)
                        {
                            rate = rate + 20;
                        }
                    }
                    else
                    {
                        if (Where.equals(RWhere))
                        {
                            rate = rate + 20;
                        }
                    }

                    if (Bed.equals(RBed))
                    {
                        rate = rate + 20;
                    }
                    if (RPrice > Price)
                    {
                        rate = rate + 20;
                    }
                    if(Child.equals("Dont"))
                    {
                        rate = rate + 10;
                    }
                    else
                        {
                            if (Child.equals(RChild))
                            {
                            rate = rate + 10;
                            }
                        }

                    if(With.equals("Dont"))
                    {
                        rate = rate + 10;
                    }
                    else
                    {
                        if (With.equals(RWith))
                        {
                            rate = rate + 10;
                        }
                    }

                    if(Power.equals("Dont"))
                    {
                        rate = rate + 10;
                    }
                    else
                    {
                        if (Power.equals(RPower))
                        {
                            rate = rate + 10;
                        }
                    }

                    if(Late.equals("Dont"))
                    {
                        rate = rate + 10;
                    }
                    else
                    {
                        if (Late.equals(RLate))
                        {
                            rate = rate + 10;
                        }
                    }
                    if (rate > 60 )
                    {
                        list.add("Place : "+ownersProfile.getWhere().toString()+"\n"+"Bed : "+ownersProfile.getBed()+"\n"+"Match : "+rate+" %");
                        count = count + 1;
                        displayed.add(Id);
                        if (RWhere.equals("Near"))
                        {
                            distanceHouse.add(" "+dis);
                        }
                        else
                        {
                            distanceHouse.add("No");
                        }
                        rate = 0;
                    }
                }
                    rentList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        rentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FindHome.this,HouseProfile.class);
                String HouseId = displayed.get(position);
                String userChoice = FWhere.getText().toString();
                //float dist = Float.parseFloat(distanceHouse.get(position));
                String dist = String.valueOf(distanceHouse.get(position));
                intent.putExtra("houseid",HouseId);
                intent.putExtra("renterid",UsersId);
                intent.putExtra("form",""+0);
                if (userChoice.equals("Near"))
                {
                    intent.putExtra("dis",""+dist);
                }
                else
                {
                    intent.putExtra("dis","No");
                }
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });/*
        rentList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FindHome.this,HouseProfile.class);
                String HouseId = displayed.get(position);
                intent.putExtra("houseid",HouseId);
                intent.putExtra("renterid",UsersId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        rentList3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FindHome.this,HouseProfile.class);
                String HouseId = displayed.get(position);
                intent.putExtra("houseid",HouseId);
                intent.putExtra("renterid",UsersId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });*/

       /*
        */
        /*databaseReference.child("owners").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot items : dataSnapshot.getChildren())
                {
                    String oWhere = items.child("where").getValue().toString();
                    String oBed = items.child("bed").getValue().toString();
                    String oChild = items.child("children").getValue().toString();
                    int oprice = Integer.parseInt(items.child("price").getValue().toString());
                    String oLate = items.child("late").getValue().toString();
                    String oPower = items.child("power").getValue().toString();
                    String oWith = items.child("with").getValue().toString();
                    String oid = items.child("oid").getValue().toString();
*//*
                    if (RWhere != oWhere || RPrice < oprice)
                    {
                        Toast.makeText(getApplicationContext(),"No Home Found Yet",Toast.LENGTH_LONG).show();
                    }
                    else
                    {*//*
                    house_display.append("\n " + oWhere);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
*/
    }
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
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
