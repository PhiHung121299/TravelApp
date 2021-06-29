package com.rajendra.vacationtourapp.CnPhu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rajendra.vacationtourapp.Login_Registration.ProfileActivity;
import com.rajendra.vacationtourapp.R;

import java.io.IOException;

public class ThayAnhDaiDien extends AppCompatActivity {
    Button btLuu, btChon;
    ImageView imgHinh;
    int REQUEST_CODE_IMAGE = 1;
    private static final int CHOOSE_IMAGE = 101;
    public Uri imageUri;
    String id;
    private FirebaseStorage storage;
    private StorageReference storageReference;


    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thay_anh_dai_dien);
        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        AnhXa();
        btChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CODE_IMAGE);
                showImageChooser();
            }
        });

    }

    private void AnhXa() {
        btChon = (Button) findViewById(R.id.buttonChonAnh);
        btLuu = (Button) findViewById(R.id.buttonLuu);
        imgHinh = (ImageView) findViewById(R.id.imageView);
    }



    private void UpdataPicture() {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("Đang tải lên...");
        pd.show();
        StorageReference riverRef =
                storageReference.child("profilepics/" + System.currentTimeMillis() + ".jpg");
        riverRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                pd.dismiss();
                Snackbar.make(findViewById(android.R.id.content), "Tải ảnh lên", Snackbar.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Không thể tải lên", Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                pd.setMessage("Tải lên" + (int) progressPercent + " %");
            }
        });

    }


    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgHinh.setImageURI(imageUri);

            UpdataPicture();
        }
    }
}