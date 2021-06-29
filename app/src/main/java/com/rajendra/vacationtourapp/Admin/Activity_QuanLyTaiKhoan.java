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
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.Admin.Sua.Activity_Sua_tt_User;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.Admin.admin_adapter.adapter_quanly_taikhoan;
import com.rajendra.vacationtourapp.UserOject;

import java.util.ArrayList;

public class Activity_QuanLyTaiKhoan extends AppCompatActivity {

    ListView lv;
    // ListView lv_dssp;
    ArrayList<UserOject> list;
    adapter_quanly_taikhoan adapter;
    int ps;
    Toolbar toolbar;
    private static final String TAG = "ok";
    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__quan_ly_tai_khoan);

        toolbar = (Toolbar) findViewById(R.id.tool_qltk);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Anhxa();

        list = new ArrayList<UserOject>();

        myData = FirebaseDatabase.getInstance().getReference().child("Users");
        myData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserOject top = dataSnapshot.getValue(UserOject.class);
                    String clubkey = dataSnapshot.getKey();

                    list.add(top);
                }
                adapter = new adapter_quanly_taikhoan(Activity_QuanLyTaiKhoan.this, R.layout.item_admin_taikhoan, list);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_QuanLyTaiKhoan.this, "opss....................", Toast.LENGTH_SHORT).show();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Activity_QuanLyTaiKhoan.this, Activity_Sua_tt_User.class);
                intent.putExtra("thongtinuser", list.get(i));
                startActivity(intent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                ps = i;
                return false;
            }
        });
        registerForContextMenu(lv);

    }

    /*
   Context Menu
  */
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
            AlertDialog.Builder builder = new AlertDialog.Builder(Activity_QuanLyTaiKhoan.this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final String mail = list.get(ps).getgMail();
                    Toast.makeText(Activity_QuanLyTaiKhoan.this, "" + mail, Toast.LENGTH_SHORT).show();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query query = ref.child("Users").orderByChild("gMail").equalTo(mail);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String key = ds.getKey();
                                myData = FirebaseDatabase.getInstance().getReference().child("Users").child(key);
                                Toast.makeText(Activity_QuanLyTaiKhoan.this, "opss...................." + key, Toast.LENGTH_SHORT).show();
                                myData.removeValue();
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());
                        }
                    });
                    list.remove(ps);
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

                    Intent intent = new Intent(Activity_QuanLyTaiKhoan.this, Activity_Sua_tt_User.class);
                    //   intent.putExtra("thongtinuser", list.get(ps));
                    startActivity(intent);
                    break;
            }

        }
        return super.onContextItemSelected(item);
    }

    private void Anhxa() {

        lv = (ListView) findViewById(R.id.lv_qltk_admin);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_QuanLyTaiKhoan.this, AdminPage.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();

        if (item.getItemId() == R.id.menu_themtk) {
            Intent intent = new Intent(Activity_QuanLyTaiKhoan.this, admin_themtaikhoan.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_themtaikhoan, menu);
        return super.onCreateOptionsMenu(menu);
    }


}