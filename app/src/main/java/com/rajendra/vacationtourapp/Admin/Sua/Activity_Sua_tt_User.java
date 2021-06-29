package com.rajendra.vacationtourapp.Admin.Sua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.Admin.Activity_QuanLyTaiKhoan;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.UserOject;

import java.util.HashMap;

public class Activity_Sua_tt_User extends AppCompatActivity {
    private static final int CHOOSE_IMAGE = 101;

    String keyNhaNghi = "";
    Toolbar toolbar;
    Spinner spinner;
    TextView tv_loaitk, tv_gmail;
    EditText edt_MK, edt_gmail, edt_hoten, edt_diachi, edt_ngaysinh, edt_sdt, edt_nhaplaiMk;
    String hoten, ngaysinh, gmail, sdt, diachi, loaitk;
    Button bt_luuthongtin;
    ProgressBar progressBar;
    DatabaseReference ref, root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sua_tt__user);
        ref = FirebaseDatabase.getInstance().getReference();
        Anhxa();
        EvenSpinner();
        getdata();
        getKeyTK();
        bt_luuthongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sdt = edt_sdt.getText() + "";
                diachi = edt_diachi.getText() + "";
                ngaysinh = edt_ngaysinh.getText() + "";
                hoten = edt_hoten.getText() + "";
                loaitk = tv_loaitk.getText() + "";
                HashMap result = new HashMap<>();
                result.put("diaChi", diachi);
                result.put("hoTen", hoten);
                result.put("loai", loaitk);
                result.put("sDt", sdt);
                result.put("ngaySinh", ngaysinh);
                //   result.put("vitriMap", vitriMap);
                root = FirebaseDatabase.getInstance().getReference().child("Users").child(keyNhaNghi);
                root.updateChildren(result).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Activity_Sua_tt_User.this, "Sửa thành công", Toast.LENGTH_LONG).show();
                    }
                });
                Log.i("data", "------------===========>>>>>>" + keyNhaNghi + ">>.>>>>>>>");
                Intent intent = new Intent(Activity_Sua_tt_User.this, Activity_QuanLyTaiKhoan.class);
                startActivity(intent);
            }
        });
    }

    private void getKeyTK() {
        Log.i("Keyyyyyyyyyyyyyyyyyyy", "----------------------------" + gmail);
        ref.child("Users").orderByChild("gMail").equalTo(gmail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        keyNhaNghi = data.getKey();
                        Log.i("Keyyyyyyyyyyyyyyyyyyy", "----------------------------" + keyNhaNghi);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void Anhxa() {
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        spinner = (Spinner) findViewById(R.id.spinner_loaitk);
        tv_gmail = (TextView) findViewById(R.id.tv_sua_gmail);
        edt_diachi = (EditText) findViewById(R.id.edt_sua_diachi);
        edt_ngaysinh = (EditText) findViewById(R.id.edt_sua_ngaysinh);
        edt_hoten = (EditText) findViewById(R.id.edt_sua_hoten);
        edt_sdt = (EditText) findViewById(R.id.edt_sua_sdt);
        bt_luuthongtin = (Button) findViewById(R.id.bt_suatk);
        tv_loaitk = (TextView) findViewById(R.id.tv_loaitk);

    }

    private void getdata() {

        UserOject nd = (UserOject) getIntent().getSerializableExtra("thongtinuser");
        edt_hoten.setText(nd.getHoTen());
        edt_sdt.setText(nd.getsDt());
        edt_diachi.setText(nd.getDiaChi());
        tv_gmail.setText(nd.getgMail());
        tv_loaitk.setText(nd.getLoai());
        edt_ngaysinh.setText(nd.getNgaySinh());
        gmail = nd.getgMail();

    }

    public void EvenSpinner() {
        final String[] loaitk = new String[]{"admin", "user"};
        ArrayAdapter<String> data = new ArrayAdapter<String>(Activity_Sua_tt_User.this, android.R.layout.simple_spinner_dropdown_item, loaitk);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Sua_tt_User.this, Activity_QuanLyTaiKhoan.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}