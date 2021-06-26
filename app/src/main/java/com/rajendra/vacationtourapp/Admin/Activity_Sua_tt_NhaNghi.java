package com.rajendra.vacationtourapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.Admin.admin_adapter.adapter_ql_nhanghi;
import com.rajendra.vacationtourapp.HomePage.HomePage;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.HinhAnh;
import com.rajendra.vacationtourapp.model.NhaNghi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_Sua_tt_NhaNghi extends AppCompatActivity {
    EditText edt_Ten, edt_sdt, edt_diachi, edt_Tongquan, edt_vitri, edt_linkanh;
    Button bt_luu;
    Toolbar toolbar;
    int Ma;
    String keyNhaNghi = "";
    String ten, diaChi, diemDanhGia, hinhAnh, sdt, tongQuan, vitriMap;
    private static final String TAG = "ok";
    DatabaseReference ref, root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sua_tt__nha_nghi);
        ref = FirebaseDatabase.getInstance().getReference();
        toolbar = (Toolbar) findViewById(R.id.toolbar_sua);
        toolbar.setTitle("Sửa thông tin");
        setSupportActionBar(toolbar);
        anhxa();
        getdata();
        Log.i("Keyyyyyyyyyyyyyyyyyyy", "----------------------------" + Ma);
        ref.child("NhaNghi").orderByChild("iDDiaDiem").equalTo(Ma).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot != null) {
                    for (DataSnapshot data : snapshot.getChildren()) {
                        keyNhaNghi = data.getKey();


                        //  Log.i("data", "------------===========>>>>>>" + keyNhaNghi + "-----------------------" + ten + sdt + hinhAnh + ">>.>>>>>>>");
                        //    root = FirebaseDatabase.getInstance().getReference().child("NhaNghi").child(""+nodekey);
//                                root.updateChildren(result).addOnSuccessListener(new OnSuccessListener() {
//                                    @Override
//                                    public void onSuccess(Object o) {
//                                        Toast.makeText(Activity_Sua_tt_NhaNghi.this, "Your Data is Successfully Update", Toast.LENGTH_LONG).show();
//                                    }
//                                });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        bt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ten = edt_Ten.getText() + "";
                sdt = edt_sdt.getText() + "";
                diaChi = edt_diachi.getText() + "";
                tongQuan = edt_Tongquan.getText() + "";
                hinhAnh = edt_linkanh.getText() + "";
                vitriMap = edt_vitri.getText() + "";
                HashMap result = new HashMap<>();
                  result.put("diaChi", diaChi);
                    result.put("hinhAnh", hinhAnh);
                 result.put("ten", ten);
                  result.put("sdt", sdt);
                result.put("tongQuan", tongQuan);
                //   result.put("vitriMap", vitriMap);
                root = FirebaseDatabase.getInstance().getReference().child("NhaNghi").child(keyNhaNghi);
                root.updateChildren(result).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(Activity_Sua_tt_NhaNghi.this, "Sửa thành công", Toast.LENGTH_LONG).show();
                    }
                });
                Log.i("data", "------------===========>>>>>>" + keyNhaNghi + ">>.>>>>>>>");
                Intent intent = new Intent(Activity_Sua_tt_NhaNghi.this,Activity_QuanLyNhaNghi.class);
                startActivity(intent);
            }
        });
    }

    private void getdata() {

        NhaNghi nn = (NhaNghi) getIntent().getSerializableExtra("updatesp");
        edt_Ten.setText(nn.getTen());
        edt_sdt.setText(nn.getSdt());
        edt_diachi.setText(nn.getDiaChi());
        edt_Tongquan.setText(nn.getTongQuan());
        edt_linkanh.setText(nn.getHinhAnh());
        edt_vitri.setText(nn.getVitriMap());
        Ma = nn.getiDDiaDiem();



    }

    public void anhxa() {
        edt_Ten = (EditText) findViewById(R.id.edt_TenHS);
        edt_linkanh = (EditText) findViewById(R.id.edt_linkanh_hs);
        edt_diachi = (EditText) findViewById(R.id.edt_diachi_hs);
        edt_sdt = (EditText) findViewById(R.id.edt_sdt_hs);
        edt_vitri = (EditText) findViewById(R.id.edt_vitrimap);
        edt_Tongquan = (EditText) findViewById(R.id.edt_mota);
        bt_luu = (Button) findViewById(R.id.bt_luu);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Sua_tt_NhaNghi.this, Activity_QuanLyNhaNghi.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}