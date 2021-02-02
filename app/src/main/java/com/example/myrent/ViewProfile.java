package com.example.myrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {

    TextView username,email,price,bed,child,power,late,phone,with, where;
    DatabaseReference reference;
    CardView rq1_edit,rq2_edit,rq3_edit,rq4_edit,rq5_edit,rq6_edit,rq7_edit;
    CardView rq1_update,rq2_update,rq3_update,rq4_update,rq5_update,rq6_update,rq7_update;
    LinearLayout rq1_layout,rq2_layout,rq3_layout,rq4_layout,rq5_layout,rq6_layout,rq7_layout;
    private Toolbar toolbar;
    Animation slideleft,slideright;
    int q1=0,q2=0,q3=0,q4=0,q5=0,q6=0,q7=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        slideleft= AnimationUtils.loadAnimation(ViewProfile.this,R.anim.slide_out_left);
        slideright= AnimationUtils.loadAnimation(ViewProfile.this,R.anim.slide_in_right);


        /*     INTAILIZING THE HIDDEN LAYOUTS    */
        rq1_layout = (LinearLayout) findViewById(R.id.rq1_layout_edit);
        rq2_layout = (LinearLayout) findViewById(R.id.rq2_layout_edit);
        rq3_layout = (LinearLayout) findViewById(R.id.rq3_layout_edit);
        rq4_layout = (LinearLayout) findViewById(R.id.rq4_layout_edit);
        rq5_layout = (LinearLayout) findViewById(R.id.rq5_layout_edit);
        rq6_layout = (LinearLayout) findViewById(R.id.rq6_layout_edit);
        rq7_layout = (LinearLayout) findViewById(R.id.rq7_layout_edit);


        /*     INTAILIZING THE BUTTONS      */
        rq1_edit = (CardView) findViewById(R.id.rq1_edit);
        rq2_edit = (CardView) findViewById(R.id.rq2_edit);
        rq3_edit = (CardView) findViewById(R.id.rq3_edit);
        rq4_edit = (CardView) findViewById(R.id.rq4_edit);
        rq5_edit = (CardView) findViewById(R.id.rq5_edit);
        rq6_edit = (CardView) findViewById(R.id.rq6_edit);
        rq7_edit = (CardView) findViewById(R.id.rq7_edit);
        rq1_update = (CardView) findViewById(R.id.rq1_update_btn);
        rq2_update = (CardView) findViewById(R.id.rq2_update_btn);
        rq3_update = (CardView) findViewById(R.id.rq3_update_btn);
        rq4_update = (CardView) findViewById(R.id.rq4_update_btn);
        rq5_update = (CardView) findViewById(R.id.rq5_update_btn);
        rq6_update = (CardView) findViewById(R.id.rq6_update_btn);
        rq7_update = (CardView) findViewById(R.id.rq7_update_btn);

        /*     INTAIALIAZING THE VIEW ELEMENTS FOR THE PROFILE         */
        username = (TextView) findViewById(R.id.pname);
        email = (TextView) findViewById(R.id.pemail);
        phone = (TextView) findViewById(R.id.pphone);
        where = (TextView) findViewById(R.id.rq1_answear);
        price = (TextView) findViewById(R.id.rq2_answear);
        bed = (TextView) findViewById(R.id.rq3_answear);
        child = (TextView) findViewById(R.id.rq4_answear);
        late = (TextView) findViewById(R.id.rq5_answear);
        power = (TextView) findViewById(R.id.rq6_answear);
        with = (TextView) findViewById(R.id.rq7_answear);

        final String UsersId = getIntent().getStringExtra("userid");
        String Class = getIntent().getStringExtra("class");
        int i = Integer.parseInt(Class);
        if (i == 0) {
            reference = FirebaseDatabase.getInstance().getReference().child("users").child(UsersId);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String UserName = dataSnapshot.child("username").getValue().toString();
                    String Email = dataSnapshot.child("email").getValue().toString();
                    String Where = dataSnapshot.child("where").getValue().toString();
                    String Price = dataSnapshot.child("price").getValue().toString();
                    String Bed = dataSnapshot.child("bed").getValue().toString();
                    String Child = dataSnapshot.child("children").getValue().toString();
                    String Late = dataSnapshot.child("late").getValue().toString();
                    String Power = dataSnapshot.child("power").getValue().toString();
                    String With = dataSnapshot.child("with").getValue().toString();
                    String Phone = dataSnapshot.child("phone").getValue().toString();
                    phone.append(Phone);
                    username.append(UserName);
                    email.append(Email);
                    where.append(Where);
                    price.append(Price);
                    bed.append(Bed);
                    child.append(Child);
                    late.append(Late);
                    power.append(Power);
                    with.append(With);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        /*       WHEN THE EDIT BUTTONS ARE CLIKED        */
        rq1_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q1==0)
                {
                    rq1_layout.setVisibility(View.VISIBLE);
                    rq1_layout.setAnimation(slideright);
                    q1 = 1;
                }
                else
                {
                    rq1_layout.setVisibility(View.INVISIBLE);
                    rq1_layout.setAnimation(slideright);
                    q1 = 0;
                }
            }
        });
        rq2_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q2 ==0)
                {
                    rq2_layout.setVisibility(View.VISIBLE);
                    rq2_layout.setAnimation(slideright);
                    q2 = 1;
                }
                else
                {
                    rq2_layout.setVisibility(View.INVISIBLE);
                    rq2_layout.setAnimation(slideright);
                    q2 = 0;
                }
            }
        });
        rq3_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q3 ==0)
                {
                    rq3_layout.setVisibility(View.VISIBLE);
                    rq3_layout.setAnimation(slideright);
                    q3 = 1;
                }
                else
                {
                    rq3_layout.setVisibility(View.INVISIBLE);
                    rq3_layout.setAnimation(slideright);
                    q3 = 0;
                }
            }
        });
        rq4_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q4 ==0)
                {
                    rq4_layout.setVisibility(View.VISIBLE);
                    rq4_layout.setAnimation(slideright);
                    q4 = 1;
                }
                else
                {
                    rq4_layout.setVisibility(View.INVISIBLE);
                    rq4_layout.setAnimation(slideright);
                    q4 = 0;
                }
            }
        });
        rq5_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q5 ==0)
                {
                    rq5_layout.setVisibility(View.VISIBLE);
                    rq5_layout.setAnimation(slideright);
                    q5 = 1;
                }
                else
                {
                    rq5_layout.setVisibility(View.INVISIBLE);
                    rq5_layout.setAnimation(slideright);
                    q5 = 0;
                }
            }
        });
        rq6_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q6 ==0)
                {
                    rq6_layout.setVisibility(View.VISIBLE);
                    rq6_layout.setAnimation(slideright);
                    q6 = 1;
                }
                else
                {
                    rq6_layout.setVisibility(View.INVISIBLE);
                    rq6_layout.setAnimation(slideright);
                    q6 = 0;
                }
            }
        });
        rq7_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (q7 ==0)
                {
                    rq7_layout.setVisibility(View.VISIBLE);
                    rq7_layout.setAnimation(slideright);
                    q7 = 1;
                }
                else
                {
                    rq7_layout.setVisibility(View.INVISIBLE);
                    rq7_layout.setAnimation(slideright);
                    q7 = 0;
                }
            }
        });

        rq1_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reference.child("users").child(UsersId).setValue("username",UsersId);
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
