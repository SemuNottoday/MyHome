package com.example.myrent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class OwnerForm extends AppCompatActivity {

    /*    Declaring the variables for the first and Second question */
    CardView oq1_Near,oq1_Bole,oq1_Amede,oq1_Mebrat,oq1_Franko,oq1_next,oq2_next;
    int ch=0,ch2=0,ch3=0,ch4=0,ch5=0;
    RelativeLayout oq1_Layout,oq2_Layout;
    SeekBar seekBar2;
    TextView priceTag2;
    int progress=2580;

    /*    Declaring the variables for the Third question */
    CardView oq3_Service,oq3_Two,oq3_Three,oq3_One,oq3_next;
    int ch6=0,ch7=0,ch8=0,ch9=0;
    RelativeLayout oq3_Layout;

    /*    Declaring the variables for the forth question */
    CardView oq4_Yes,oq4_No,oq4_next,oq4_Dont;
    RelativeLayout oq4_Layout;
    int ch10=0,ch11=0,ch30=0;

    /*    Declaring the variables for the fifth question */
    CardView oq5_Yes,oq5_No,oq5_next,oq5_Dont;
    RelativeLayout oq5_Layout;
    int ch12=0,ch13=0,ch31=0;

    /*    Declaring the variables for the six question */
    CardView oq6_Yes,oq6_No,oq6_next,oq6_Dont;
    RelativeLayout oq6_Layout;
    int ch14=0,ch15=0,ch32=0;

    /*    Declaring the variables for the six question */
    CardView oq7_Yes,oq7_No,oq7_next,oq7_Dont;
    RelativeLayout oq7_Layout;
    int ch16=0,ch17=0,ch18=0;

    /*    Declaring the variables for filling the forms */
    RelativeLayout profileLayout;
    EditText userName,email,password,phone;
    CardView onext;
    ArrayList<String> ownerchoice;
    TextView Cemail,Cphone,Cpass,Cuname,Cprice,Cwhere,Cbed,Cchild,Clate,Cpower,Clive,Clatitude,Clongtitude;
    DatabaseReference databaseReference,databaseReference2;
    Animation fadein,sclaleup,slideleft,slideright,scaledown,slideup,slideup2,slideup3,slideup4;
    OwnersProfile ownerProfile;
    private Toolbar toolbar;
    String AmedeLati="8.531948",AmedeLong="39.282076",BoleLati="8.569299",BoleLong="39.284538",MebratLati="8.549302",MebraLong="39.276907",FrankoLati="8.541249",FrankoLongo="39.264318";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_form);
        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ownerchoice=new ArrayList<String>();
        databaseReference = FirebaseDatabase.getInstance().getReference("owners");
        databaseReference2 = FirebaseDatabase.getInstance().getReference("auth");
        ownerProfile = new OwnersProfile();

        /*            INITAIALIZING THE OWNERS CHOICE INTERFACE         */
        Cbed=(TextView) findViewById(R.id.obed);
        Cchild=(TextView) findViewById(R.id.ochild);
        Cprice=(TextView) findViewById(R.id.oprice);
        Cpower=(TextView) findViewById(R.id.opower);
        Clive=(TextView) findViewById(R.id.live);
        Cwhere = (TextView) findViewById(R.id.owhere);
        Clate =(TextView) findViewById(R.id.olate);
        Cuname = (EditText) findViewById(R.id.ousername);
        Cpass = (EditText) findViewById(R.id.opassword);
        Cemail = (EditText) findViewById(R.id.oemail);
        Cphone = (EditText) findViewById(R.id.ophone);
        Clatitude = (TextView) findViewById(R.id.olatitude_display);
        Clongtitude = (TextView) findViewById(R.id.olongtitude_display);

                /*    INITIALIZING THE LAYOUT FOR ALL QUESTIONS    */
        oq1_Layout=(RelativeLayout) findViewById(R.id.oq1_layout);
        oq2_Layout=(RelativeLayout) findViewById(R.id.oq2_layout);
        oq3_Layout=(RelativeLayout) findViewById(R.id.oq3_layout);
        oq4_Layout=(RelativeLayout) findViewById(R.id.oq4_layout);
        oq5_Layout=(RelativeLayout) findViewById(R.id.oq5_layout);
        oq6_Layout=(RelativeLayout) findViewById(R.id.oq6_layout);
        oq7_Layout=(RelativeLayout) findViewById(R.id.oq7_layout);
        profileLayout=(RelativeLayout) findViewById(R.id.profile_layout_for_owner);

     /*               BUTTONS FOR NAVIGATING THROUGH THE QUESTIONS     */
        oq1_next=(CardView) findViewById(R.id.oq1_next);
        oq1_next.setVisibility(View.INVISIBLE);
        oq2_next=(CardView) findViewById(R.id.oq2_next);
        oq2_next.setVisibility(View.INVISIBLE);
        oq3_next=(CardView) findViewById(R.id.oq3_next);
        oq3_next.setVisibility(View.INVISIBLE);
        oq4_next=(CardView) findViewById(R.id.oq4_next);
        oq4_next.setVisibility(View.INVISIBLE);
        oq5_next=(CardView) findViewById(R.id.oq5_next);
        oq5_next.setVisibility(View.INVISIBLE);
        oq6_next=(CardView) findViewById(R.id.oq6_next);
        oq6_next.setVisibility(View.INVISIBLE);
        oq7_next=(CardView) findViewById(R.id.oq7_next);
        oq7_next.setVisibility(View.INVISIBLE);
        onext = (CardView) findViewById(R.id.oprofile_send);

        /*          INTIALAIZING THE ANIMATION FOR THE APP   */

        fadein= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.fadein);
        slideup= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.slide_up);
        slideleft= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.slide_out_left);
        slideright= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.slide_in_right);
        slideup2= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.slide_up2);
        slideup3= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.slide_up3);
        slideup4= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.slide_up4);
        scaledown= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.zoomin);
        sclaleup= AnimationUtils.loadAnimation(OwnerForm.this,R.anim.zoomout);

         /*For the first Question Choices */
        oq1_Near=(CardView) findViewById(R.id.oq1_here);
        oq1_Near.startAnimation(slideup);
        oq1_Near.setOnClickListener(new View.OnClickListener() {
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
                    ownerchoice.add("Near");
                    oq1_next.setVisibility(View.VISIBLE);
                    oq1_Near.setCardBackgroundColor(Color.RED);
                    Toast.makeText(getApplicationContext(),""+lan+"   "+lon,Toast.LENGTH_LONG).show();
                }
                /*if (ch == 0)
                {
                    oq1_Near.setCardBackgroundColor(Color.RED);
                    oq1_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Near");
                    ch=1;
                }
                else if (ch == 1)
                {
                    oq1_Near.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Near");
                    ownerchoice.remove(i);
                    ch=0;
                }*/
            }
        });

        oq1_Bole=(CardView) findViewById(R.id.oq1_bole);
        oq1_Bole.startAnimation(slideup2);
        oq1_Bole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch2 == 0)
                {
                    oq1_Bole.setCardBackgroundColor(Color.RED);
                    oq1_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Bole");
                    ch2=1;
                }
                else if (ch2 == 1)
                {
                    oq1_Bole.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Bole");
                    ownerchoice.remove(i);
                    ch2=0;
                }
            }
        });
        oq1_Franko=(CardView) findViewById(R.id.oq1_franko);
        oq1_Franko.startAnimation(slideup3);
        oq1_Franko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch3 == 0)
                {
                    oq1_Franko.setCardBackgroundColor(Color.RED);
                    oq1_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Franko");
                    ch3=1;
                }
                else if (ch3 == 1)
                {
                    oq1_Franko.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Franko");
                    ownerchoice.remove(i);
                    ch3=0;
                }
            }
        });

        oq1_Mebrat=(CardView) findViewById(R.id.oq1_mebrat);
        oq1_Mebrat.startAnimation(fadein);
        oq1_Mebrat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch4 == 0)
                {
                    oq1_Mebrat.setCardBackgroundColor(Color.RED);
                    oq1_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Mebrat");
                    ch4=1;
                }
                else if (ch4 == 1)
                {
                    oq1_Mebrat.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Mebrat");
                    ownerchoice.remove(i);
                    ch4=0;
                }
            }
        });

        oq1_Amede=(CardView) findViewById(R.id.oq1_amede);
        oq1_Amede.startAnimation(fadein);
        oq1_Amede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch5 == 0)
                {
                    oq1_Amede.setCardBackgroundColor(Color.RED);
                    oq1_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Amede");
                    ch5=1;
                }
                else if (ch5 == 1)
                {
                    oq1_Amede.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Amede");
                    ownerchoice.remove(i);
                    ch5=0;
                }
            }
        });

        oq1_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),""+ownerchoice,Toast.LENGTH_LONG).show();
                String choose = ownerchoice.get(0);
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
                oq1_Layout.setVisibility(View.INVISIBLE);
                oq1_Layout.startAnimation(slideleft);
                oq2_Layout.setVisibility(View.VISIBLE);
                oq2_Layout.startAnimation(slideright);
                Cwhere.setText(""+ownerchoice.get(0));

            }
        });


          /*   FOR THE SECOND QUESTION       */

        seekBar2=(SeekBar) findViewById(R.id.seekBar2);
        seekBar2.setMax(10000);
        seekBar2.setProgress(progress);

        priceTag2=(TextView) findViewById(R.id.priceView2);
        priceTag2.setText(""+progress);

        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                progress = i ;
                priceTag2.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                oq2_next.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        oq2_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String price = priceTag2.getText().toString();
                ownerchoice.add(price);
                Cprice.setText(""+ownerchoice.get(1));
                oq2_Layout.setVisibility(View.INVISIBLE);
                oq2_Layout.startAnimation(slideleft);
                oq3_Layout.setVisibility(View.VISIBLE);
                oq3_Layout.startAnimation(slideright);
            }
        });


          /* FOR THE THIRD QUESTION         */
        oq3_Service=(CardView) findViewById(R.id.oq3_service);
        oq3_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch6 == 0)
                {
                    oq3_Service.setCardBackgroundColor(Color.RED);
                    oq3_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Service");
                    ch6=1;
                }
                else if(ch6 == 1 )
                {
                    oq3_Service.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Service");
                    ownerchoice.remove(i);
                    ch6=0;
                }
            }
        });

        oq3_Two=(CardView) findViewById(R.id.oq3_two);
        oq3_Two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch7 == 0)
                {
                    oq3_Two.setCardBackgroundColor(Color.RED);
                    oq3_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Two");
                    ch7=1;
                }
                else if(ch7 == 1 )
                {
                    oq3_Two.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Two");
                    ownerchoice.remove(i);
                    ch7=0;
                }
            }
        });

        oq3_Three=(CardView) findViewById(R.id.oq3_three);
        oq3_Three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch8 == 0)
                {
                    oq3_Three.setCardBackgroundColor(Color.RED);
                    oq3_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Three");
                    ch8=1;
                }
                else if(ch8 == 1 )
                {
                    oq3_Three.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Three");
                    ownerchoice.remove(i);
                    ch8=0;
                }
            }
        });

        oq3_One=(CardView) findViewById(R.id.oq3_one);
        oq3_One.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch9 == 0)
                {
                    oq3_One.setCardBackgroundColor(Color.RED);
                    oq3_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("One");
                    ch9=1;
                }
                else if(ch9 == 1 )
                {
                    oq3_One.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("One");
                    ownerchoice.remove(i);
                    ch9=0;
                }
            }
        });

        oq3_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cbed.setText(""+ownerchoice.get(2));
                oq3_Layout.setVisibility(View.INVISIBLE);
                oq3_Layout.startAnimation(slideleft);
                oq4_Layout.setVisibility(View.VISIBLE);
                oq4_Layout.startAnimation(slideright);
            }
        });


             /* FOR THE FOURTH QUESTIONS */
        oq4_Dont=(CardView) findViewById(R.id.oq4_dont);
        oq4_Dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch30 == 0)
                {
                    oq4_Dont.setCardBackgroundColor(Color.RED);
                    oq4_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Dont");
                    ch30=1;
                }
                else if(ch30 == 1 )
                {
                    oq4_Dont.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Dont");
                    ownerchoice.remove(i);
                    ch30=0;
                }

            }
        });
        oq4_Yes=(CardView) findViewById(R.id.oq4_yes);
        oq4_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch10 == 0)
                {
                    oq4_Yes.setCardBackgroundColor(Color.RED);
                    oq4_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Yes");
                    ch10=1;
                }
                else if(ch10 == 1 )
                {
                    oq4_Yes.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Yes");
                    ownerchoice.remove(i);
                    ch10=0;
                }

            }
        });

        oq4_No=(CardView) findViewById(R.id.oq4_no);
        oq4_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch10 == 0)
                {
                    oq4_No.setCardBackgroundColor(Color.RED);
                    oq4_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("No");
                    ch10=1;
                }
                else if(ch10 == 1 )
                {
                    oq4_No.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("No");
                    ownerchoice.remove(i);
                    ch10=0;
                }

            }
        });

        oq4_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),""+ownerchoice,Toast.LENGTH_LONG).show();
                Cchild.setText(""+ownerchoice.get(3));
                oq4_Layout.setVisibility(View.INVISIBLE);
                oq4_Layout.startAnimation(slideleft);
                oq5_Layout.setVisibility(View.VISIBLE);
                oq5_Layout.startAnimation(slideright);
            }
        });

             /* FOR THE FIFTH QUESTIONS */
        oq5_Dont=(CardView) findViewById(R.id.oq5_dont);
        oq5_Dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch31 == 0)
                {
                    oq5_Dont.setCardBackgroundColor(Color.RED);
                    oq5_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Dont");
                    ch31=1;
                }
                else if(ch31 == 1 )
                {
                    oq5_Dont.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Dont");
                    ownerchoice.remove(i);
                    ch31=0;
                }

            }
        });
        oq5_Yes=(CardView) findViewById(R.id.oq5_yes);
        oq5_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch12 == 0)
                {
                    oq5_Yes.setCardBackgroundColor(Color.RED);
                    oq5_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Yes");
                    ch12=1;
                }
                else if(ch12 == 1 )
                {
                    oq5_Yes.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Yes");
                    ownerchoice.remove(i);
                    ch12=0;
                }

            }
        });

        oq5_No=(CardView) findViewById(R.id.oq5_no);
        oq5_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch13 == 0)
                {
                    oq5_No.setCardBackgroundColor(Color.RED);
                    oq5_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("No");
                    ch13=1;
                }
                else if(ch13 == 1 )
                {
                    oq5_No.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("No");
                    ownerchoice.remove(i);
                    ch13=0;
                }

            }
        });

        oq5_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getApplicationContext(),""+ownerchoice,Toast.LENGTH_LONG).show();
                Clate.setText(""+ownerchoice.get(4));
                oq5_Layout.setVisibility(View.INVISIBLE);
                oq5_Layout.startAnimation(slideleft);
                oq6_Layout.setVisibility(View.VISIBLE);
                oq6_Layout.startAnimation(slideright);
            }
        });

           /*   FOR THE SIX QUESTIONS */
        oq6_Dont=(CardView) findViewById(R.id.oq6_dont);
        oq6_Dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch32 == 0)
                {
                    oq6_Dont.setCardBackgroundColor(Color.RED);
                    oq6_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Dont");
                    ch32=1;
                }
                else if(ch32 == 1 )
                {
                    oq6_Dont.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Dont");
                    ownerchoice.remove(i);
                    ch32=0;
                }

            }
        });
        oq6_Yes=(CardView) findViewById(R.id.oq6_yes);
        oq6_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch14 == 0)
                {
                    oq6_Yes.setCardBackgroundColor(Color.RED);
                    oq6_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Yes");
                    ch14=1;
                }
                else if(ch14 == 1 )
                {
                    oq6_Yes.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Yes");
                    ownerchoice.remove(i);
                    ch14=0;
                }

            }
        });

        oq6_No=(CardView) findViewById(R.id.oq6_no);
        oq6_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch15 == 0)
                {
                    oq6_No.setCardBackgroundColor(Color.RED);
                    oq6_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("No");
                    ch15=1;
                }
                else if(ch15 == 1 )
                {
                    oq6_No.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("No");
                    ownerchoice.remove(i);
                    ch15=0;
                }

            }
        });

        oq6_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),""+ownerchoice,Toast.LENGTH_LONG).show();
                Cpower.setText(""+ownerchoice.get(5));
                oq6_Layout.setVisibility(View.INVISIBLE);
                oq6_Layout.startAnimation(slideleft);
                oq7_Layout.setVisibility(View.VISIBLE);
                oq7_Layout.startAnimation(slideright);
            }
        });

            /*  FOR THE SEVEN QUESTIONS */
        oq7_Yes=(CardView) findViewById(R.id.oq7_yes);
        oq7_Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch16 == 0)
                {
                    oq7_Yes.setCardBackgroundColor(Color.RED);
                    oq7_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("Yes");
                    ch16=1;
                }
                else if(ch16 == 1 )
                {
                    oq7_Yes.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("Yes");
                    ownerchoice.remove(i);
                    ch16=0;
                }

            }
        });

        oq7_No=(CardView) findViewById(R.id.oq7_no);
        oq7_No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ch17 == 0)
                {
                    oq7_No.setCardBackgroundColor(Color.RED);
                    oq7_next.setVisibility(View.VISIBLE);
                    ownerchoice.add("No");
                    ch17=1;
                }
                else if(ch17 == 1 )
                {
                    oq7_No.setCardBackgroundColor(Color.rgb(67,171,73));
                    int  i=ownerchoice.indexOf("No");
                    ownerchoice.remove(i);
                    ch17=0;
                }

            }
        });

        oq7_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(OwnerForm.this,HomePage.class);
                intent.putStringArrayListExtra("preference",ownerchoice);
                startActivity(intent);*/
                Clive.setText(""+ownerchoice.get(6));
                oq7_Layout.setVisibility(View.INVISIBLE);
                oq7_Layout.startAnimation(slideleft);
                profileLayout.setVisibility(View.VISIBLE);
                profileLayout.startAnimation(slideright);
            }
        });

        onext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRenter();
            }
        });
    }


    public void addRenter()
    {

        String UserName = Cuname.getText().toString();
        String Password = Cpass.getText().toString();
        String Email = Cemail.getText().toString();
        String Where = Cwhere.getText().toString();
        int Phone = Integer.parseInt(Cphone.getText().toString());
        String Bed = Cbed.getText().toString();
        int Price = Integer.parseInt(Cprice.getText().toString());
        String Children = Cchild.getText().toString();
        String Late = Clate.getText().toString();
        String Power = Cpower.getText().toString();
        String Live = Clive.getText().toString();
        String Latitude = Clatitude.getText().toString();
        String Longtitude = Clongtitude.getText().toString();
        String Label = "Owner";

        String oid=databaseReference.push().getKey();

        if (!TextUtils.isEmpty(UserName) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(Email))
        {
            ownerProfile = new OwnersProfile(UserName,Email,Bed,Children,Late,Power,Live,Password,Price,Where,oid,Phone,Latitude,Longtitude);
            databaseReference.child(oid).setValue(ownerProfile);

            OwnerAuthontication authontication = new OwnerAuthontication(oid,Email,Password,Label);
            databaseReference2.child(oid).setValue(authontication);

            Toast.makeText(OwnerForm.this,"Successfully inserted",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(OwnerForm.this,OwnersHome.class);
            intent.putExtra("houseid",oid);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(OwnerForm.this,"Please Fill The Fields First",Toast.LENGTH_LONG).show();
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
