package com.example.wac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReserveActivity extends AppCompatActivity {

    String[] floor = {"1층","2층","3층","4층","5층"};

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, floor
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        //Floor data to Realtime database
        Button rs_btn = (Button) findViewById(R.id.btn_reserve);
        rs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedFloor = spinner.getSelectedItem().toString();
                char firstChar = selectedFloor.charAt(0);
                String result = String.valueOf(firstChar);
                databaseReference.child("Floor").setValue(result);
                Toast.makeText(ReserveActivity.this, "예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });


        getvalue_area();
    }

    //Get Area data to Application
    public void getvalue_area(){
        databaseReference.child("Area").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String value = dataSnapshot.getValue(String.class);
                    if (value != null) {
                        int area = Integer.parseInt(value);
                        TextView textView = findViewById(R.id.textView12);
                        textView.setText("엘레베이터 내부 사용가능 면적 : " + String.valueOf(area) + "%");
                        if (area > 50 && area <= 100) {
                            textView.setBackground(getResources().getDrawable(R.drawable.background_green));
                        }
                        if (area >= 30 && area <= 50) {
                            textView.setBackground(getResources().getDrawable(R.drawable.background_yellow));
                        }
                        if (area < 30) {
                            textView.setBackground(getResources().getDrawable(R.drawable.background_red));
                        }
                    }
                    if(value == null){
                        Toast.makeText(ReserveActivity.this, "WiFi연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ReserveActivity.this, "WiFi연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}