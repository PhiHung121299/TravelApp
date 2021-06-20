package com.rajendra.vacationtourapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.NhaNghi;

import java.util.HashMap;
import java.util.Map;

public class Activity_Sua_tt_NhaNghi extends AppCompatActivity {
    EditText edt_Ten, edt_sdt, edt_diachi, edt_Tongquan, edt_vitri, edt_linkanh;
    Button bt_luu;
    Toolbar toolbar;
    String Ma;
    private static final String TAG = "ok";
    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sua_tt__nha_nghi);
        toolbar = (Toolbar) findViewById(R.id.toolbar_sua);
        toolbar.setTitle("Sửa thông tin");
        setSupportActionBar(toolbar);
        anhxa();
        getdata();
        myData = FirebaseDatabase.getInstance().getReference().child("NhaNghi");
        bt_luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhaNghi nn = new NhaNghi();
                HashMap<String, Object> result = new HashMap<>();
                result.put("diaChi", edt_diachi.getText());
                result.put("hinhAnh", edt_linkanh.getText());
                result.put("ten", edt_Ten.getText());
                result.put("sdt", edt_sdt.getText());
                result.put("tongQuan", edt_Tongquan.getText());
                result.put("vitriMap", edt_vitri.getText());

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query query = ref.child("NhaNghi").orderByChild("iDDiaDiem").equalTo(Ma);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String key = ds.getKey();
                            myData = FirebaseDatabase.getInstance().getReference().child("NhaNghi").child(key);
                            myData.updateChildren(result);
                            finish();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
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
        Ma = String.valueOf(nn.getiDDiaDiem());
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
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}