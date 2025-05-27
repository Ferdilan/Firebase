package com.example.firebase;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
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

public class MainActivity2 extends AppCompatActivity {
    private EditText editTextUpdateNama, editTextUpdateUmur;
    private Spinner spinnerUpdateJk;
    private DatabaseReference databaseBiodata;
    private String biodataId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        biodataId = getIntent().getStringExtra("BiodataID");
        editTextUpdateNama = findViewById(R.id.editText_updateNama);
        editTextUpdateUmur = findViewById(R.id.editText_updateUmur);
        spinnerUpdateJk = findViewById(R.id.spinner_updateJk);
        databaseBiodata = FirebaseDatabase.getInstance().getReference("biodata").child(biodataId);

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
                Biodata biodata = snapshot.getValue(Biodata.class);
                if (biodata != null) {
                    String nama = biodata.getBiodata_Nama();
                    String umur = biodata.getBiodata_Umur();
                    String jk = biodata.getBiodata_JK();
                    editTextUpdateNama.setText(nama);
                    editTextUpdateUmur.setText(umur);
                    if (jk.equals("Pria")) {
                        spinnerUpdateJk.setSelection(0);
                    } else {
                        spinnerUpdateJk.setSelection(1);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void updateBiodata(View view){
        String nama = editTextUpdateNama.getText().toString();
        String umur = editTextUpdateUmur.getText().toString();
        String jk = spinnerUpdateJk.getSelectedItem().toString();

        if (!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(umur) && !TextUtils.isEmpty(jk)) {
            Biodata biodata = new Biodata(biodataId, nama, umur, jk);
            databaseBiodata.setValue(biodata).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(MainActivity2.this, "Biodata berhasil diupdate", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }else{
            Toast.makeText(this, "Semua box harus diisi", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteBiodata(View view) {
        databaseBiodata.removeValue().addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity2.this, "Biodata berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}