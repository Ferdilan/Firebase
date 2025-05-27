package com.example.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNama, editTextUmur;
    private Spinner spinnerJk;
    private ListView listViewBio;
    private List<Biodata> listBiodata;
    //database reference sebagai referensi data dari firebase
    private DatabaseReference databaseBiodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextNama = findViewById(R.id.editTextNama);
        editTextUmur = findViewById(R.id.editTextUmur);
        spinnerJk = findViewById(R.id.spinnerJk);
        listViewBio = findViewById(R.id.listView_bio); // Pastikan ID ini sesuai di XML
        listBiodata = new ArrayList<>();
        databaseBiodata = FirebaseDatabase.getInstance().getReference("biodata");

        listViewBio.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Biodata biodata = listBiodata.get(position);
                String Biodataid = biodata.getBiodata_Id();
                //mengirim id melalui intent ke mainactivity2 untuk proses udpdatedelete
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("BiodataID", Biodataid);
                startActivity(intent);
            }
        });

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        databaseBiodata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listBiodata.clear();
                    //perulangan untuk mengambil data dari firebase
                    for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                        //setiap data akan ditampung di biodataSnapshot
                        Biodata biodata = postSnapshot.getValue(Biodata.class);
                        //data akan ditambahkan ke listBiodata
                        listBiodata.add(biodata);
                    }
                    //menampilkan list yang berisi data dari firebase ke dalam listview
                    listview_biodata biodataList_Adapter = new listview_biodata(MainActivity.this, listBiodata);
                    listViewBio.setAdapter(biodataList_Adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Data gagal diambil", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public void addBiodata(View view) {
        //ambil data dari EditText dan Spinner.
        String nama = editTextNama.getText().toString();
        String umur = editTextUmur.getText().toString();
        String jk = spinnerJk.getSelectedItem().toString();

        if(!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(umur) && !TextUtils.isEmpty(jk)) {
            String id = databaseBiodata.push().getKey();
            Biodata biodata = new Biodata(id, nama, umur, jk);
            databaseBiodata.child(id).setValue(biodata)
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    editTextNama.setText("");
                    editTextUmur.setText("");
                    spinnerJk.setSelection(0);
                    Toast.makeText(MainActivity.this, "Biodata berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(this, "Semua box harus diisi", Toast.LENGTH_SHORT).show();
        }
    }
}