package com.rajendra.vacationtourapp.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.Admin.Sua.Activity_Sua_tt_NhaNghi;
import com.rajendra.vacationtourapp.Admin.admin_adapter.adapter_ql_diadiem;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.model.DiaDiem;

import java.util.ArrayList;

public class Activity_QuanlyDiaDiem extends AppCompatActivity {
    Button bt_themsp;
    ListView lv_ds;
    int ps;
    // ListView lv_dssp;
    ArrayList<DiaDiem> listDiaDiem;
    adapter_ql_diadiem adapter;
    Toolbar toolbar;
    private static final String TAG = "ok";
    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__quanly_dia_diem);
        Anhxa();
        listDiaDiem = new ArrayList<DiaDiem>();

        myData = FirebaseDatabase.getInstance().getReference().child("TravelLocation");
        myData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    DiaDiem top = dataSnapshot.getValue(DiaDiem.class);
                    String clubkey = dataSnapshot.getKey();

                    listDiaDiem.add(top);
                }
                adapter = new adapter_ql_diadiem(Activity_QuanlyDiaDiem.this, R.layout.item_lv_qlnhanghi_admin, listDiaDiem);
                lv_ds.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_QuanlyDiaDiem.this, "opss....................", Toast.LENGTH_SHORT).show();
            }
        });
        lv_ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Activity_QuanlyDiaDiem.this, DetailsActivity.class);
                intent.putExtra("dsdd", listDiaDiem.get(i));
             //   Toast.makeText(Activity_QuanlyDiaDiem.this, "opss...................." + listDiaDiem.get(0).title, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        lv_ds.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ps = i;
                return false;
            }
        });
        registerForContextMenu(lv_ds);

    }

    private void Anhxa() {
        lv_ds = (ListView) findViewById(R.id.lv_qldiadiem_admin);

        toolbar = (Toolbar) findViewById(R.id.tool_qldd);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderIcon(R.drawable.btcaidat);

        menu.add(Menu.NONE, 1, menu.NONE, "Sửa");
        menu.add(Menu.NONE, 2, menu.NONE, "Xóa");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Item xóa
        if (item.getItemId() == 2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Activity_QuanlyDiaDiem.this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final String Ma = listDiaDiem.get(ps).getId();
                    Toast.makeText(Activity_QuanlyDiaDiem.this, "" + Ma, Toast.LENGTH_SHORT).show();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query query = ref.child("TravelLocation").orderByChild("id").equalTo(""+Ma);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String key = ds.getKey();
                                myData = FirebaseDatabase.getInstance().getReference().child("TravelLocation").child(key);
                                Toast.makeText(Activity_QuanlyDiaDiem.this, "opss...................." + key, Toast.LENGTH_SHORT).show();
                                myData.removeValue();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());
                        }
                    });
                    listDiaDiem.remove(ps);
                    adapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.show();

        } else {
            // Cập nhật
            switch (item.getItemId()) {
                case 1:

                    Intent intent = new Intent(Activity_QuanlyDiaDiem.this, Activity_Sua_tt_NhaNghi.class);

                    intent.putExtra("updatesp", listDiaDiem.get(ps));
                    startActivity(intent);
                    break;
            }

        }
        return super.onContextItemSelected(item);
    }
}