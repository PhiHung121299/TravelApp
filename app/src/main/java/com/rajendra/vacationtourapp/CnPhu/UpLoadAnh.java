package com.rajendra.vacationtourapp.CnPhu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rajendra.vacationtourapp.Admin.Activity_QuanlyDiaDiem;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpLoadAnh extends AppCompatActivity {
    Button upload, choose;
    TextView alert;
    Toolbar toolbar;
    ArrayList<Uri> Imagelist = new ArrayList<Uri>();
    private Uri ImageUri;
    private static final int PICK_IMAGE = 1;
    private int upload_count = 0;
    private ProgressDialog progressDialog;
    String nodekey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_load_anh);
        alert = (TextView) findViewById(R.id.alert);
        choose = (Button) findViewById(R.id.bt_chonanh);
        upload = (Button) findViewById(R.id.button_tailen);
        toolbar = (Toolbar) findViewById(R.id.toolbar_fullupload);
        toolbar.setTitle("Tải ảnh lên");
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Image Uploading please Wait..........");


        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, PICK_IMAGE);

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                alert.setText("If Loading Takes to long please Press the button again");
                StorageReference ImageFolder = FirebaseStorage.getInstance().getReference().child("ImageFolder");
                for (upload_count = 0; upload_count < Imagelist.size(); upload_count++) {

                    Uri IndividualImage = Imagelist.get(upload_count);
                    StorageReference ImageName = ImageFolder.child("Image" + IndividualImage.getLastPathSegment());
                    ImageName.putFile(IndividualImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            ImageName.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = String.valueOf(uri);
                                    StoreLink(url);
                                }
                            });
                        }
                    });
                }

            }
        });
    }

    private void StoreLink(String url) {
        Bundle extras = this.getIntent().getExtras();

        nodekey = extras.getString("nodekey");
        Log.i("data", "------------===========>>>>>>" + nodekey + ">>.>>>>>>>");
        HashMap<String, String> hashMap = new HashMap<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("TravelLocation").child("" + nodekey).child("img");
        hashMap.put("url", url);
        databaseReference.push().setValue(hashMap);
        progressDialog.dismiss();
        alert.setText("Image Uploaded Successfully");
        upload.setVisibility(View.GONE);
        Intent intent = new Intent(UpLoadAnh.this, Activity_QuanlyDiaDiem.class);

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (data.getClipData() != null) {
                    int countClipData = data.getClipData().getItemCount();
                    int currentImageSelect = 0;
                    while (currentImageSelect < countClipData) {
                        ImageUri = data.getClipData().getItemAt(currentImageSelect).getUri();
                        Imagelist.add(ImageUri);
                        currentImageSelect = currentImageSelect + 1;
                    }
                    alert.setVisibility(View.VISIBLE);
                    alert.setText("You have selected" + Imagelist.size() + "Images");
                } else {
                    Toast.makeText(this, "Please Select Multiple Image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}