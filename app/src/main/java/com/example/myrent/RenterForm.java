package com.example.myrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RenterForm extends AppCompatActivity {

    /*    Declaring the variables for the first and Second question */
    CardView rq1_Near,rq1_Bole,rq1_Amede,rq1_Mebrat,rq1_Franko,rq1_next,rq2_next;
    int ch=0,ch2=0,ch3=0,ch4=0,ch5=0;
    RelativeLayout rq1_Layout,rq2_Layout;
    SeekBar seekBar;
    TextView pricetag;
    int progress=2400;

    /*    Declaring the variables for the Third question */
    CardView rq3_Service,rq3_Two,rq3_Three,rq3_One,rq3_next;
    int ch6=0,ch7=0,ch8=0,ch9=0;
    RelativeLayout rq3_Layout;

    /*    Declaring the variables for the forth question */
    CardView rq4_Yes,rq4_No,rq4_next;
    RelativeLayout rq4_Layout;
    int ch10=0,ch11=0;

    /*    Declaring the variables for the fifth question */
    CardView rq5_Yes,rq5_No,rq5_next;
    RelativeLayout rq5_Layout;
    int ch12=0,ch13=0;

    /*    Declaring the variables for the six question */
    CardView rq6_Yes,rq6_No,rq6_next;
    RelativeLayout rq6_Layout;
    int ch14=0,ch15=0;

    /*    Declaring the variables for the six question */
    CardView rq7_Yes,rq7_No,rq7_next,rq7_Dont;
    RelativeLayout rq7_Layout;
    int ch16=0,ch17=0,ch18=0;
    /*    For the renter to fill the profile       */
    CardView rsend;
    RelativeLayout rProfile_layout;
    EditText rname,remail,rpass;

    /*        gps location finder    */
    TextView dis,dis2;
    Button btn,send;
    private LocationManager locationManager;
    private LocationListener locationListener;
    LinearLayout locationL;
        /*  Users Choice */
    TextView Cemail,Cpass,Cuname,Cprice,Cwhere,Cbed,Cchild,Clate,Cpower,Cwith,Cphone,Clatitude,Clongtitude;
    RenterProfile renterProfile;
    ArrayList<String> userchoice;
    Animation fadein,sclaleup,slideleft,slideright,scaledown,slideup,slideup2,slideup3,slideup4;
    DatabaseReference databaseReference,databaseReference2;
    private Toolbar toolbar;
    String AmedeLati="8.531948",AmedeLong="39.282076",BoleLati="8.569299",BoleLong="39.284538",MebratLati="8.549302",MebraLong="39.276907",FrankoLati="8.541249",FrankoLongo="39.264318";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_form);
        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("auth");

        renterProfile= new RenterProfile();
        userchoice=new ArrayList<String>();

        /*            INTTIALIZING THE USER CHOICE VIEW INTERFACE    */
        Cbed=(TextView) findViewById(R.id.bed);
        Cchild=(TextView) findViewById(R.id.child);
        Cprice=(TextView) findViewById(R.id.price);
        Cpower=(TextView) findViewById(R.id.power);
        Cwith=(TextView) findViewById(R.id.owner);
        Cwhere = (TextView) findViewById(R.id.where);
        Clate =(TextView) findViewById(R.id.late);
        Cemail = (TextView) findViewById(R.id.remail);
        Cpass = (TextView) findViewById(R.id.rpassword);
        Cuname = (TextView) findViewById(R.id.rusername);
        Cphone = (TextView) findViewById(R.id.rphone);
        Clatitude = (TextView) findViewById(R.id.latitude);
        Clongtitude = (TextView) findViewById(R.id.logtitude);


        /*            INITIALIZING THE LAYOUT FOR ALL QUESTIONS    */
        rq1_Layout=(RelativeLayout) findViewById(R.id.rq1_layout);
        rq2_Layout=(RelativeLayout) findViewById(R.id.rq2_layout);
        rq3_Layout=(RelativeLayout) findViewById(R.id.rq3_layout);
        rq4_Layout=(RelativeLayout) findViewById(R.id.rq4_layout);
        rq5_Layout=(RelativeLayout) findViewById(R.id.rq5_layout);
        rq6_Layout=(RelativeLayout) findViewById(R.id.rq6_layout);
        rq7_Layout=(RelativeLayout) findViewById(R.id.rq7_layout);
        rProfile_layout=(RelativeLayout) findViewById(R.id.profile_layout);
        locationL = (LinearLayout) findViewById(R.id.gps_layout);

        /*            BUTTONS FOR NAVIGATING THROUGH THE QUESTIONS     */
        rq1_next=(CardView) findViewById(R.id.rq1_next);
        rq1_next.setVisibility(View.INVISIBLE);
        rq2_next=(CardView) findViewById(R.id.rq2_next);
        rq2_next.setVisibility(View.INVISIBLE);
        rq3_next=(CardView) findViewById(R.id.rq3_next);
        rq3_next.setVisibility(View.INVISIBLE);
        rq4_next=(CardView) findViewById(R.id.rq4_next);
        rq4_next.setVisibility(View.INVISIBLE);
        rq5_next=(CardView) findViewById(R.id.rq5_next);
        rq5_next.setVisibility(View.INVISIBLE);
        rq6_next=(CardView) findViewById(R.id.rq6_next);
        rq6_next.setVisibility(View.INVISIBLE);
        rq7_next=(CardView) findViewById(R.id.rq7_next);
        rq7_next.setVisibility(View.INVISIBLE);
        rsend=(CardView) findViewById(R.id.rprofile_send);

        /*          INTIALAIZING THE ANIMATION FOR THE APP   */

        fadein= AnimationUtils.loadAnimation(RenterForm.this,R.anim.fadein);
        slideup= AnimationUtils.loadAnimation(RenterForm.this,R.anim.slide_up);
        slideleft= AnimationUtils.loadAnimation(RenterForm.this,R.anim.slide_out_left);
        slideright= AnimationUtils.loadAnimation(RenterForm.this,R.anim.slide_in_right);
        slideup2= AnimationUtils.loadAnimation(RenterForm.this,R.anim.slide_up2);
        slideup3= AnimationUtils.loadAnimation(RenterForm.this,R.anim.slide_up3);
        slideup4= AnimationUtils.loadAnimation(RenterForm.this,R.anim.slide_up4);
        scaledown= AnimationUtils.loadAnimation(RenterForm.this,R.anim.zoomin);
        sclaleup= AnimationUtils.loadAnimation(RenterForm.this,R.anim.zoomout);


        /* For the first Question Choices */
        ActivityCompat.requestPermissions(RenterForm.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        rq1_Near=(CardView) findViewById(R.id.rq1_near);
        rq1_Near.startAnimation(slideup);
        rq1_Near.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GPSTracker gps = new GPSTracker(getApplicationContext());
                Location l = gps.getLocation();
                if (l != null)
                {
                    double lan = l.getLatitude();
                    double lon = l.getLongitude();
                    Clatitude.setText(""+lan);
                    Clongtitude.setText(""+lon);
                    userchoice.add("Near");
                    rq1_next.setVisibility(View.VISIBLE);
                    rq1_Near.setCardBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(),""+lan+"   "+lon,Toast.LENGTH_LONG).show();
                }
                /*if (ch == 0)
                {
                    rq1_Near.setCardBackgroundColor(Color.RED);
                    rq1_next.setVisibility(View.VISIBLE);
                    userchoice.add("Near");
                    ch=1;
                }
                else if (ch == 1)
                {
                    rq1_Near.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Near");
                    userchoice.remove(i);
                    ch=0;
                }*/
            }
        });

        rq1_Bole=(CardView) findViewById(R.id.rq1_bole);
        rq1_Bole.startAnimation(slideup2);
        rq1_Bole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch2 == 0)
                {
                    rq1_Bole.setCardBackgroundColor(Color.RED);
                    rq1_next.setVisibility(View.VISIBLE);
                    userchoice.add("Bole");
                    ch2=1;
                }
                else if (ch2 == 1)
                {
                    rq1_Bole.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Bole");
                    userchoice.remove(i);
                    ch2=0;
                }
            }
        });
        rq1_Franko=(CardView) findViewById(R.id.rq1_franko);
        rq1_Franko.startAnimation(slideup3);
        rq1_Franko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch3 == 0)
                {
                    rq1_Franko.setCardBackgroundColor(Color.RED);
                    rq1_next.setVisibility(View.VISIBLE);
                    userchoice.add("Franko");
                    ch3=1;
                }
                else if (ch3 == 1)
                {
                    rq1_Franko.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Franko");
                    userchoice.remove(i);
                    ch3=0;
                }
            }
        });

        rq1_Mebrat=(CardView) findViewById(R.id.rq1_mebrat);
        rq1_Mebrat.startAnimation(fadein);
        rq1_Mebrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch4 == 0)
                {
                    rq1_Mebrat.setCardBackgroundColor(Color.RED);
                    rq1_next.setVisibility(View.VISIBLE);
                    userchoice.add("Mebrat");
                    ch4=1;
                }
                else if (ch4 == 1)
                {
                    rq1_Mebrat.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Mebrat");
                    userchoice.remove(i);
                    ch4=0;
                }
            }
        });

        rq1_Amede=(CardView) findViewById(R.id.rq1_amede);
        rq1_Amede.startAnimation(fadein);
        rq1_Amede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch5 == 0)
                {
                    rq1_Amede.setCardBackgroundColor(Color.RED);
                    rq1_next.setVisibility(View.VISIBLE);
                    userchoice.add("Amede");
                    ch5=1;
                }
                else if (ch5 == 1)
                {
                    rq1_Amede.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Amede");
                    userchoice.remove(i);
                    ch5=0;
                }
            }
        });

        rq1_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),""+userchoice,Toast.LENGTH_LONG).show();
                Cwhere.setText(""+userchoice.get(0));
                String choose = userchoice.get(0);
                if (choose.equals("Amede"))
                {
                    Clatitude.setText(AmedeLati);
                    Clongtitude.setText(AmedeLong);
                }

                if (choose.equals("Mebrat"))
                {
                    Clatitude.setText(AmedeLati);
                    Clongtitude.setText(AmedeLong);
                }

                if (choose.equals("Amede"))
                {
                    Clatitude.setText(MebratLati);
                    Clongtitude.setText(MebraLong);
                }

                if (choose.equals("Bole"))
                {
                    Clatitude.setText(BoleLati);
                    Clongtitude.setText(BoleLong);
                }

                if (choose.equals("Franko"))
                {
                    Clatitude.setText(FrankoLati);
                    Clongtitude.setText(FrankoLongo);
                }
                rq1_Layout.setVisibility(View.INVISIBLE);
                rq1_Layout.startAnimation(slideleft);
                rq2_Layout.setVisibility(View.VISIBLE);
                rq2_Layout.startAnimation(slideright);
            }
        });


        /*  For the GPS finder interface     */
/*

        btn = (Button) findViewById(R.id.location_btn);
        dis = (TextView) findViewById(R.id.latitude_display);
        dis2 = (TextView) findViewById(R.id.longtitude_display);
        send = (Button) findViewById(R.id.send_location);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double radial = (latitude / 180) * 3.14378;
                double miles = radial * 69.172;
                dis.setText(""+miles);
                dis2.setText(""+location.getLongitude());
                userchoice.add("bla");

                rq2_Layout.setVisibility(View.VISIBLE);
                rq2_Layout.startAnimation(slideleft);
                locationL.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);
                return;
            }
        } else {
            configureButton();
        }
*/

        /*send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Latitude = dis.getText().toString();
                String Longtitude = dis2.getText().toString();
                Clatitude.setText(Latitude);
                Clongtitude.setText(Longtitude);
                rq2_Layout.setVisibility(View.VISIBLE);
                location.setVisibility(View.INVISIBLE);
            }
        });*/

        /*     FOR THE SECOND QUESTION          */

        seekBar=(SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(9000);
        seekBar.setProgress(progress);

        pricetag=(TextView) findViewById(R.id.priceView);
        pricetag.setText(""+progress);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                progress = i;
                pricetag.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                rq2_next.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rq2_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price=pricetag.getText().toString();
                Cprice.setText(""+price);
                rq2_Layout.setVisibility(View.INVISIBLE);
                rq2_Layout.startAnimation(slideleft);
                rq3_Layout.setVisibility(View.VISIBLE);
                rq3_Layout.startAnimation(slideright);
            }
        });


        /*   FOR THE THIRD QUESTION               */
        rq3_Service=(CardView) findViewById(R.id.rq3_service);
        rq3_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch6 == 0)
                {
                    rq3_Service.setCardBackgroundColor(Color.RED);
                    rq3_next.setVisibility(View.VISIBLE);
                    userchoice.add("Service");
                    ch6=1;
                }
                else if(ch6 == 1 )
                {
                    rq3_Service.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Service");
                    userchoice.remove(i);
                    ch6=0;
                }
            }
        });

        rq3_Two=(CardView) findViewById(R.id.rq3_two);
        rq3_Two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch7 == 0)
                {
                    rq3_Two.setCardBackgroundColor(Color.RED);
                    rq3_next.setVisibility(View.VISIBLE);
                    userchoice.add("Two");
                    ch7=1;
                }
                else if(ch7 == 1 )
                {
                    rq3_Two.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Two");
                    userchoice.remove(i);
                    ch7=0;
                }
            }
        });

        rq3_Three=(CardView) findViewById(R.id.rq3_three);
        rq3_Three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch8 == 0)
                {
                    rq3_Three.setCardBackgroundColor(Color.RED);
                    rq3_next.setVisibility(View.VISIBLE);
                    userchoice.add("Three");
                    ch8=1;
                }
                else if(ch8 == 1 )
                {
                    rq3_Three.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Three");
                    userchoice.remove(i);
                    ch8=0;
                }
            }
        });

        rq3_One=(CardView) findViewById(R.id.rq3_one);
        rq3_One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch9 == 0)
                {
                    rq3_One.setCardBackgroundColor(Color.RED);
                    rq3_next.setVisibility(View.VISIBLE);
                    userchoice.add("One");
                    ch9=1;
                }
                else if(ch9 == 1 )
                {
                    rq3_One.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("One");
                    userchoice.remove(i);
                    ch9=0;
                }
            }
        });

        rq3_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cbed.setText(""+userchoice.get(1));
                rq3_Layout.setVisibility(View.INVISIBLE);
                rq3_Layout.startAnimation(slideleft);
                rq4_Layout.setVisibility(View.VISIBLE);
                rq4_Layout.startAnimation(slideright);
            }
        });


        /*      FOR THE FOURTH QUESTIONS */
        rq4_Yes=(CardView) findViewById(R.id.rq4_yes);
        rq4_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch10 == 0)
                {
                    rq4_Yes.setCardBackgroundColor(Color.RED);
                    rq4_next.setVisibility(View.VISIBLE);
                    userchoice.add("Yes");
                    ch10=1;
                }
                else if(ch10 == 1 )
                {
                    rq4_Yes.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Yes");
                    userchoice.remove(i);
                    ch10=0;
                }

            }
        });

        rq4_No=(CardView) findViewById(R.id.rq4_no);
        rq4_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch10 == 0)
                {
                    rq4_No.setCardBackgroundColor(Color.RED);
                    rq4_next.setVisibility(View.VISIBLE);
                    userchoice.add("No");
                    ch10=1;
                }
                else if(ch10 == 1 )
                {
                    rq4_No.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("No");
                    userchoice.remove(i);
                    ch10=0;
                }

            }
        });

        rq4_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),""+userchoice,Toast.LENGTH_LONG).show();
                Cchild.setText(""+userchoice.get(2));
                rq4_Layout.setVisibility(View.INVISIBLE);
                rq4_Layout.startAnimation(slideleft);
                rq5_Layout.setVisibility(View.VISIBLE);
                rq5_Layout.startAnimation(slideright);
            }
        });

        /*      FOR THE FIFTH QUESTIONS */
        rq5_Yes=(CardView) findViewById(R.id.rq5_yes);
        rq5_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch12 == 0)
                {
                    rq5_Yes.setCardBackgroundColor(Color.RED);
                    rq5_next.setVisibility(View.VISIBLE);
                    userchoice.add("Yes");
                    ch12=1;
                }
                else if(ch12 == 1 )
                {
                    rq5_Yes.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Yes");
                    userchoice.remove(i);
                    ch12=0;
                }

            }
        });

        rq5_No=(CardView) findViewById(R.id.rq5_no);
        rq5_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch13 == 0)
                {
                    rq5_No.setCardBackgroundColor(Color.RED);
                    rq5_next.setVisibility(View.VISIBLE);
                    userchoice.add("No");
                    ch13=1;
                }
                else if(ch13 == 1 )
                {
                    rq5_No.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("No");
                    userchoice.remove(i);
                    ch13=0;
                }

            }
        });

        rq5_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),""+userchoice,Toast.LENGTH_LONG).show();
                Clate.setText(""+userchoice.get(3));
                rq5_Layout.setVisibility(View.INVISIBLE);
                rq5_Layout.startAnimation(slideleft);
                rq6_Layout.setVisibility(View.VISIBLE);
                rq6_Layout.startAnimation(slideright);
            }
        });

        /*      FOR THE SIX QUESTIONS */
        rq6_Yes=(CardView) findViewById(R.id.rq6_yes);
        rq6_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch14 == 0)
                {
                    rq6_Yes.setCardBackgroundColor(Color.RED);
                    rq6_next.setVisibility(View.VISIBLE);
                    userchoice.add("Yes");
                    ch14=1;
                }
                else if(ch14 == 1 )
                {
                    rq6_Yes.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Yes");
                    userchoice.remove(i);
                    ch14=0;
                }

            }
        });

        rq6_No=(CardView) findViewById(R.id.rq6_no);
        rq6_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch15 == 0)
                {
                    rq6_No.setCardBackgroundColor(Color.RED);
                    rq6_next.setVisibility(View.VISIBLE);
                    userchoice.add("No");
                    ch15=1;
                }
                else if(ch15 == 1 )
                {
                    rq6_No.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("No");
                    userchoice.remove(i);
                    ch15=0;
                }

            }
        });

        rq6_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),""+userchoice,Toast.LENGTH_LONG).show();
                Cpower.setText(""+userchoice.get(4));
                rq6_Layout.setVisibility(View.INVISIBLE);
                rq6_Layout.startAnimation(slideleft);
                rq7_Layout.setVisibility(View.VISIBLE);
                rq7_Layout.startAnimation(slideright);
            }
        });

        /*      FOR THE SEVEN QUESTIONS */
        rq7_Yes=(CardView) findViewById(R.id.rq7_yes);
        rq7_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch16 == 0)
                {
                    rq7_Yes.setCardBackgroundColor(Color.RED);
                    rq7_next.setVisibility(View.VISIBLE);
                    userchoice.add("Yes");
                    ch16=1;
                }
                else if(ch16 == 1 )
                {
                    rq7_Yes.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("Yes");
                    userchoice.remove(i);
                    ch16=0;
                }

            }
        });

        rq7_No=(CardView) findViewById(R.id.rq7_no);
        rq7_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch17 == 0)
                {
                    rq7_No.setCardBackgroundColor(Color.RED);
                    rq7_next.setVisibility(View.VISIBLE);
                    userchoice.add("No");
                    ch17=1;
                }
                else if(ch17 == 1 )
                {
                    rq7_No.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("No");
                    userchoice.remove(i);
                    ch17=0;
                }

            }
        });
        rq7_Dont=(CardView) findViewById(R.id.rq7_dont);
        rq7_Dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch18 == 0)
                {
                    rq7_Dont.setCardBackgroundColor(Color.RED);
                    rq7_next.setVisibility(View.VISIBLE);
                    userchoice.add("No");
                    ch18=1;
                }
                else if(ch18 == 1 )
                {
                    rq7_Dont.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=userchoice.indexOf("No");
                    userchoice.remove(i);
                    ch18=0;
                }

            }
        });
        rq7_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cwith.setText(""+userchoice.get(5));
                rq7_Layout.setVisibility(View.INVISIBLE);
                rq7_Layout.startAnimation(slideleft);
                rProfile_layout.setVisibility(View.VISIBLE);
                rProfile_layout.startAnimation(slideright);
            }
        });


        rsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRenter();
            }
        });
    }
    /*@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configureButton();
                return;
        }
    }

    private void configureButton() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                locationManager.requestLocationUpdates("gps", 80000, 5, locationListener);
            }
        });
    }*/
   ///////////   ANOTHER WAY
    public void addRenter()
    {

        String UserName = Cuname.getText().toString();
        String Password = Cpass.getText().toString();
        String Email = Cemail.getText().toString();
        String Where = Cwhere.getText().toString();
        String Bed = Cbed.getText().toString();
        int Price = Integer.parseInt(Cprice.getText().toString());
        String Children = Cchild.getText().toString();
        String Late = Clate.getText().toString();
        String Power = Cpower.getText().toString();
        String With = Cwith.getText().toString();
        String Phone = Cphone.getText().toString();
        String Latitude = Clatitude.getText().toString();
        String Longtitude = Clongtitude.getText().toString();
        String Label ="Renter";
        String id=databaseReference.push().getKey();
        String id2=databaseReference2.push().getKey();

        if (!TextUtils.isEmpty(UserName) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(Email))
        {
            RenterProfile renterProfile = new RenterProfile(id,UserName,Email,Password,Bed,Children,Late,Power,With,Price,Where,Phone,Latitude,Longtitude);
            databaseReference.child(id).setValue(renterProfile);

            UserAuthontication user = new UserAuthontication(id,Email,Password,Label);
            databaseReference2.child(id2).setValue(user);

            Toast.makeText(RenterForm.this,"Successfully inserted",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(RenterForm.this,MyHome.class);
            intent.putExtra("userid",id);
            intent.putExtra("class",""+0);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(RenterForm.this,"Please Fill The Fields First",Toast.LENGTH_LONG).show();
        }
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
