package com.example.myrent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.microedition.khronos.egl.EGLDisplay;

public class LogIn extends AppCompatActivity {

    EditText user,pass;
    CardView login;
    TextView wrong;
    private Toolbar toolbar;
    DatabaseReference dRef;
    UserAuthontication userAuthontication;
    OwnerAuthontication ownerAuthontication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dRef = FirebaseDatabase.getInstance().getReference("auth");
        userAuthontication = new UserAuthontication();
        ownerAuthontication = new OwnerAuthontication();
        user = (EditText) findViewById(R.id.loginusername);
        pass = (EditText) findViewById(R.id.loginpassword);
        login = (CardView) findViewById(R.id.btnlogin);
        wrong = (TextView) findViewById(R.id.loginWarnning);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String LEmail = user.getText().toString();
                final String LPass = pass.getText().toString();
                if (TextUtils.isEmpty(LEmail))
                {
                    wrong.setText("Please Insert Name");
                }
                else if (TextUtils.isEmpty(LPass))
                {
                    wrong.setText("Please Insert Password");
                }else
                {
                    dRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot item: dataSnapshot.getChildren())
                            {
                                userAuthontication = item.getValue(UserAuthontication.class);
                                ownerAuthontication = item.getValue(OwnerAuthontication.class);
                                String UEmail = userAuthontication.getEmail();
                                String UPass  = userAuthontication.getPassword();
                                String Label  = userAuthontication.getLabel();
                                if (LEmail.equals(UEmail))
                                {
                                    if (LPass.equals(UPass))
                                    {
                                        if (Label.equals("Renter"))
                                        {
                                            String UserId = userAuthontication.getUid();
                                            Intent intent = new Intent(LogIn.this,MyHome.class);
                                            intent.putExtra("userid",UserId);
                                            intent.putExtra("class",""+0);
                                            startActivity(intent);
                                        }
                                        else if (Label.equals("Owner"))
                                        {
                                            String HouseId= ownerAuthontication.getId();
                                            Intent intent = new Intent(LogIn.this,OwnersHome.class);
                                            intent.putExtra("houseid",HouseId);
                                            startActivity(intent);
                                        }
                                    }
                                    else
                                    {
                                        wrong.setText("Wrong Password");

                                    }
                                }
                                else
                                {
                                    wrong.setText("Wrong Email");

                                }


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
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
