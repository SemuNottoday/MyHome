package com.example.myrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HouseProfile extends AppCompatActivity {

    TextView houseWhere,houseLate,housePrice,housePower,houseWith,houseChild,houseDistance,houseLati,houseLongi,ownerPhone;
    TextView houseBed,houseId,renterId,houseEmail,renterEmail,ownerName,renterName;
    private Toolbar toolbar;
    DatabaseReference dRef,dRef2,dRef3;
    CardView emailowner,addFav,seePic;
    String emailSubject = "About House For Rent";
    Animation animation;
    LinearLayout btnLayout;
    ImageView favorImg;
    int favInt = 0;
    /*              For calling through the app            */
    private static final int REQUEST_CALL = 1;
    CardView callowner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_profile);
        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        houseBed = (TextView) findViewById(R.id.house_bed);
        houseWith = (TextView) findViewById(R.id.house_with);
        houseChild = (TextView) findViewById(R.id.houser_Children);
        houseLate = (TextView) findViewById(R.id.house_late);
        housePower = (TextView) findViewById(R.id.house_power);
        housePrice = (TextView) findViewById(R.id.house_price);
        houseWhere = (TextView) findViewById(R.id.houser_where);
        houseId = (TextView) findViewById(R.id.house_id);
        renterId = (TextView) findViewById(R.id.renter_id);
        houseEmail = (TextView) findViewById(R.id.house_email);
        renterEmail = (TextView) findViewById(R.id.renter_email);
        renterName = (TextView) findViewById(R.id.renter_name);
        ownerName = (TextView) findViewById(R.id.owner_name);
        houseLati = (TextView) findViewById(R.id.house_lati);
        houseLongi = (TextView) findViewById(R.id.house_longi);
        houseDistance = (TextView) findViewById(R.id.house_distance);
        ownerPhone = (TextView) findViewById(R.id.owner_phone_no);

        addFav = (CardView) findViewById(R.id.add_to_favorite);
        emailowner = (CardView) findViewById(R.id.Send_email);
        favorImg = (ImageView) findViewById(R.id.favorite_image);
        seePic = (CardView) findViewById(R.id.see_photos);

        btnLayout = (LinearLayout) findViewById(R.id.button_layout);
        btnLayout.setVisibility(View.INVISIBLE);
        final String HouseId = getIntent().getStringExtra("houseid");
        String UserID = getIntent().getStringExtra("renterid");
        String distance = getIntent().getStringExtra("dis");
        if (distance.equals("No"))
        {
            houseDistance.setVisibility(View.INVISIBLE);
        }
        else
        {
            houseDistance.append(""+distance+" KM");
        }
        int From = Integer.parseInt(getIntent().getStringExtra("form"));
        if (From == 1)
        {
            btnLayout.setVisibility(View.VISIBLE);
            favorImg.setImageResource(R.drawable.ic_favorite_black_24dp);
        }

        houseId.setText(HouseId);
        renterId.setText(UserID);
        dRef3 = FirebaseDatabase.getInstance().getReference().child("users").child(UserID);
        dRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Email = dataSnapshot.child("email").getValue().toString();
                String UserName = dataSnapshot.child("username").getValue().toString();
                renterName.setText(UserName);
                renterEmail.setText(Email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        dRef = FirebaseDatabase.getInstance().getReference().child("owners").child(HouseId);
        dRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String UserName = dataSnapshot.child("username").getValue().toString();
                String Email = dataSnapshot.child("email").getValue().toString();
                String Phone = dataSnapshot.child("phone").getValue().toString();
                String Where = dataSnapshot.child("where").getValue().toString();
                String Price = dataSnapshot.child("price").getValue().toString();
                String Bed = dataSnapshot.child("bed").getValue().toString();
                String Child = dataSnapshot.child("children").getValue().toString();
                String Late = dataSnapshot.child("late").getValue().toString();
                String Power = dataSnapshot.child("power").getValue().toString();
                String With = dataSnapshot.child("live").getValue().toString();
                String Latitude = dataSnapshot.child("latitude").getValue().toString();
                String Longtitude = dataSnapshot.child("longtitude").getValue().toString();
                ownerName.setText(UserName);
                houseEmail.setText(Email);
                houseWhere.append(Where);
                housePrice.append(Price);
                houseBed.append(Bed);
                houseChild.append(Child);
                houseLate.append(Late);
                housePower.append(Power);
                houseWith.append(With);
                houseLati.setText(Latitude);
                houseLongi.setText(Longtitude);
                ownerPhone.setText(Phone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        dRef2 = FirebaseDatabase.getInstance().getReference("favorite");
        addFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favInt == 0){
                    favorImg.setImageResource(R.drawable.ic_favorite_black_24dp);
                    addFavorite();
                }
                else
                {
                    favorImg.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                }
            }
        });

        emailowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ownersName = ownerName.getText().toString();
                String renterName2 = renterName.getText().toString();
                String message = "Dear MR. "+ownersName+" My name is "+renterName2+" and i will like to " +
                        "rent your house. everything seems to match with my preference, if you like i " +
                        "would love to come over and see the house.";
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("mailTo" + houseEmail.getText().toString()));
                        intent.putExtra(Intent.EXTRA_SUBJECT,emailSubject);
                        intent.putExtra(Intent.EXTRA_TEXT,message);
                        startActivity(intent);

            }
        });

        seePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Latitude = houseLati.getText().toString();
                String Longtitude = houseLongi.getText().toString();
                String Name = ownerName.getText().toString();
                Intent intent = new Intent(HouseProfile.this,HouseOnMAP.class);
                intent.putExtra("lati",Latitude);
                intent.putExtra("longi",Longtitude);
                intent.putExtra("name",Name);
                startActivity(intent);
            }
        });

        /*              to call through the app       */
        callowner = findViewById(R.id.call_owner);
        callowner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });
    }

    private void makePhoneCall()
    {
        String PhoneNo = ownerPhone.getText().toString();
        if (PhoneNo.trim().length() > 0)
        {
            if (ContextCompat.checkSelfPermission(HouseProfile.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(HouseProfile.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else
            {
                String dial = "tel:" + PhoneNo;
                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Calling is not avaliable for this house",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                makePhoneCall();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void addFavorite()
    {

        String hid = houseId.getText().toString();
        String rid = renterId.getText().toString();
        String where = houseWhere.getText().toString();
        String price = housePrice.getText().toString();

        String id=dRef2.push().getKey();
        FavoriteClass fav = new FavoriteClass(rid,hid,id,where,price);
            dRef2.child(id).setValue(fav);
            Toast.makeText(HouseProfile.this,"Successfully added",Toast.LENGTH_LONG).show();
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
