package com.example.myrent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class OwnersHome extends AppCompatActivity {

    CardView upload,send;
    ImageView img;
    StorageReference storageReference;
    DatabaseReference dRef;
    private Uri imguri;
    private StorageTask task;
    String HouseId;
    TextView dis;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owners_home);
        toolbar = findViewById(R.id.toolBarHeader);
        setSupportActionBar(toolbar);

        HouseId = getIntent().getStringExtra("houseid");
        storageReference = FirebaseStorage.getInstance().getReference("images");
        dRef = FirebaseDatabase.getInstance().getReference("uploads");
        dis = (TextView) findViewById(R.id.houseid_display);
        dis.setText(HouseId);
        img = (ImageView) findViewById(R.id.image_display);
        upload = (CardView) findViewById(R.id.upload_image);
        send = (CardView) findViewById(R.id.upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fileChooser();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (task != null && task.isInProgress())
                {
                    Toast.makeText(getApplicationContext(),"Uploading inProgress",Toast.LENGTH_LONG).show();
                }
                else {
                    FileUploader();
                }
            }
        });
    }

    private String getExtension(Uri uri)
    {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void FileUploader()
    {
        final String timestamp = String.valueOf(System.currentTimeMillis());
        StorageReference Ref = storageReference.child(System.currentTimeMillis()+"."+getExtension(imguri));
        task = Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();

                        String uploadId=dRef.push().getKey();
                        String houseId = dis.getText().toString();
                        ImageConstructor imag =new ImageConstructor(uploadId,imguri,houseId);
                        dRef.child(uploadId).setValue(imag);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {

                    }
                });
    }
    private void fileChooser()
    {
        Intent intent = new Intent();
        intent.setType("image/'");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logout:
                Intent intent2 = new Intent(OwnersHome.this,MainActivity.class);
                startActivity(intent2);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            imguri = data.getData();
            img.setImageURI(imguri);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu st) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,st);

        return super.onCreateOptionsMenu(st);
    }
}
