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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rajendra.vacationtourapp.Admin.admin_adapter.adapter_ql_nhanghi;
import com.rajendra.vacationtourapp.ChitietNhaNghi;
import com.rajendra.vacationtourapp.DetailsActivity;
import com.rajendra.vacationtourapp.HomePage.HomePage;
import com.rajendra.vacationtourapp.R;
import com.rajendra.vacationtourapp.adapter.AllNhaNghiAdapter;
import com.rajendra.vacationtourapp.model.NhaNghi;

import java.util.ArrayList;

public class Activity_QuanLyNhaNghi extends AppCompatActivity {
    Button bt_themsp;
    ListView lv_ds;
    int ps;
    // ListView lv_dssp;
    ArrayList<NhaNghi> listNhaNghi;
    adapter_ql_nhanghi adapter;
    Toolbar toolbar;
    private static final String TAG = "ok";
    DatabaseReference myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__quan_ly_nha_nghi);
        Anhxa();


        listNhaNghi = new ArrayList<NhaNghi>();

        myData = FirebaseDatabase.getInstance().getReference().child("NhaNghi");
        myData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    NhaNghi top = dataSnapshot.getValue(NhaNghi.class);
                    String clubkey = dataSnapshot.getKey();
                    //Toast.makeText(Activity_QuanLyNhaNghi.this, "opss...................." + clubkey, Toast.LENGTH_SHORT).show();
                    listNhaNghi.add(top);
                }
                adapter = new adapter_ql_nhanghi(Activity_QuanLyNhaNghi.this, R.layout.item_lv_qlnhanghi_admin, listNhaNghi);
                // Toast.makeText(Activity_QuanLyNhaNghi.this, "opss...................." + listNhaNghi.get(0), Toast.LENGTH_SHORT).show();
                lv_ds.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_QuanLyNhaNghi.this, "opss....................", Toast.LENGTH_SHORT).show();
            }
        });
        lv_ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Activity_QuanLyNhaNghi.this, ChitietNhaNghi.class);
                intent.putExtra("dsnn", listNhaNghi.get(i));
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
        lv_ds = (ListView) findViewById(R.id.lv_qlnhanghi_admin);

        toolbar = (Toolbar) findViewById(R.id.tool_qlnn);
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
            AlertDialog.Builder builder = new AlertDialog.Builder(Activity_QuanLyNhaNghi.this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa không ?");
            builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    final int Ma = listNhaNghi.get(ps).getiDDiaDiem();
                    Toast.makeText(Activity_QuanLyNhaNghi.this, "" + Ma, Toast.LENGTH_SHORT).show();
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    Query query = ref.child("NhaNghi").orderByChild("iDDiaDiem").equalTo(Ma);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                String key = ds.getKey();
                                myData = FirebaseDatabase.getInstance().getReference().child("NhaNghi").child(key);
                                Toast.makeText(Activity_QuanLyNhaNghi.this, "opss...................."+key, Toast.LENGTH_SHORT).show();
                                myData.removeValue();
                                finish();

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e(TAG, "onCancelled", databaseError.toException());
                        }
                    });
                    listNhaNghi.remove(ps);
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

                    Intent intent = new Intent(Activity_QuanLyNhaNghi.this, Activity_Sua_tt_NhaNghi.class);
                    // intent.putExtra("id", listsp.get(i).getMaSP());
                    intent.putExtra("updatesp", listNhaNghi.get(ps));
                    startActivity(intent);
                    //    intentMenu = new Intent(activity_loaibk.this, activity_loaibk_update.class);
                    break;
            }

        }
        return super.onContextItemSelected(item);
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Activity_QuanLyNhaNghi.this, AdminPage.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}