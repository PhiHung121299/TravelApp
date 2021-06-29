package com.rajendra.vacationtourapp.Admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.rajendra.vacationtourapp.Login_Registration.ProfileActivity;
import com.rajendra.vacationtourapp.Login_Registration.SignUpActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.UserOject;

import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class admin_themtaikhoan extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 101;
    Uri filepath;
    private FirebaseAuth mAuth;
    Bitmap bitmap;
    ImageView imageView;
    Toolbar toolbar;
    Spinner spinner;
    TextView tv_loaitk;
    EditText edt_MK, edt_gmail, edt_hoten, edt_diachi, edt_ngaysinh, edt_sdt, edt_nhaplaiMk;
    Button bt_themtk;
    String str_tk, str_mk, str_hoten, str_diachi, str_ngaysinh, str_sdt, str_gmail, str_spinner;
    String linkmacdinh = "https://firebasestorage.googleapis.com/v0/b/travelapp-3003b.appspot.com/o/profilepics%2Ffacebookprofile.jpg?alt=media&token=d4dd53a6-fb95-4b10-a471-a9a8449e33d3";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_themtaikhoan);
        toolbar = (Toolbar) findViewById(R.id.tool_themtk);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAuth = FirebaseAuth.getInstance();
        Anhxa();
        EvenSpinner();
        bt_themtk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Themtaikhoan();
                //  registerUser();
            }
        });

    }

    public void Themtaikhoan() {
        String mk1,mk2;
        if (edt_gmail.getText().toString().equals("")) {
            Toast.makeText(this, "Nhập gmail", Toast.LENGTH_SHORT).show();
        } else if (edt_MK.getText().toString().equals("")) {
            Toast.makeText(this, "Nhập mật khẩu", Toast.LENGTH_SHORT).show();
        } else if (edt_hoten.getText().toString().equals("")) {
            Toast.makeText(this, "Nhập họ tên", Toast.LENGTH_SHORT).show();
        } else if (edt_ngaysinh.getText().toString().equals("")) {
            Toast.makeText(this, "Nhập ngày sinh", Toast.LENGTH_SHORT).show();
        } else if (edt_sdt.getText().toString().equals("")) {
            Toast.makeText(this, "Nhập số điện thoại", Toast.LENGTH_SHORT).show();
        } else if (edt_diachi.getText().toString().equals("")) {
            Toast.makeText(this, "Nhập địa chỉ", Toast.LENGTH_SHORT).show();

        } else {
            registerUser();
        }
    }

    private void Anhxa() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        spinner = (Spinner) findViewById(R.id.spinner_loaitk);
        edt_MK = (EditText) findViewById(R.id.edt_add_matkhau_dangky);
        edt_gmail = (EditText) findViewById(R.id.edt_add_gmail);
        edt_diachi = (EditText) findViewById(R.id.edt_add_diachi);
        edt_ngaysinh = (EditText) findViewById(R.id.edt_add_ngaysinh);
        edt_hoten = (EditText) findViewById(R.id.edt_add_hoten);
        edt_sdt = (EditText) findViewById(R.id.edt_add_sdt);
        bt_themtk = (Button) findViewById(R.id.bt_luutk);
        tv_loaitk = (TextView) findViewById(R.id.tv_loaitk);

    }

    private void showImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Profile Image"), CHOOSE_IMAGE);
    }

    private void registerUser() {
        String email = edt_gmail.getText().toString().trim();
        String password = edt_MK.getText().toString().trim();
        String hoten = edt_hoten.getText().toString().trim();
        String loai = tv_loaitk.getText().toString().trim();
        String ngaysinh = edt_ngaysinh.getText().toString().trim();
        String sdt = edt_sdt.getText().toString().trim();
        String diachi = edt_diachi.getText().toString().trim();

        UserOject user = new UserOject(hoten, loai, ngaysinh, linkmacdinh, email, sdt, diachi);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {

                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(admin_themtaikhoan.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(admin_themtaikhoan.this, "Đăng ký không thành công", Toast.LENGTH_LONG).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });

                    finish();

                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            filepath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (Exception ex) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void loadUserInformation() {
        final FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            if (user.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(user.getPhotoUrl().toString())
                        .into(imageView);
            }
        }
    }

    public void EvenSpinner() {
        final String[] loaitk = new String[]{"admin", "user"};
        ArrayAdapter<String> data = new ArrayAdapter<String>(admin_themtaikhoan.this, android.R.layout.simple_spinner_dropdown_item, loaitk);
        spinner.setAdapter(data);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                tv_loaitk.setText(loaitk[arg2]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

}
