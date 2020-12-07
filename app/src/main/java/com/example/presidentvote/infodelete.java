package com.example.presidentvote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class infodelete extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    ImageView iv_profile;
    TextView tv_name;
    TextView tv_cs;
    TextView tv_gender;
    TextView tv_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infodelete);
        setTitle("삭제화면");

        tv_name = (TextView)findViewById(R.id.name);
        tv_cs = (TextView)findViewById(R.id.cs);
        tv_gender = (TextView)findViewById(R.id.gender);
        iv_profile = (ImageView)findViewById(R.id.imageView);
        tv_num = (TextView)findViewById(R.id.num);

        Intent intent = getIntent();
        String str_name = intent.getStringExtra("name");
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("candidate");
        myRef.child(str_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String a = snapshot.child("name").getValue(String.class);
                tv_name.setText("이름 : " + a);
                a = snapshot.child("cs").getValue(String.class);
                tv_cs.setText("학년 : " + a);
                a = snapshot.child("profile").getValue(String.class);
                Glide.with(getApplicationContext()).load(a).into(iv_profile);
                a = snapshot.child("gender").getValue(String.class);
                tv_gender.setText("성별 : " + a);
                a = String.valueOf(snapshot.child("num").getValue(int.class));
                tv_num.setText("투표수 : "+a);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.infodelete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_btn3) {
            Intent intent = new Intent(this, managerview.class);
            startActivity(intent);
            finish();
            return true;
        } else if(id == R.id.action_btn5) {
            Intent gintent = getIntent();
            String str_name = gintent.getStringExtra("name");

            database = FirebaseDatabase.getInstance();
            myRef = database.getReference("candidate/" + str_name);
            myRef.removeValue();

            Intent intent = new Intent(this, managerview.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}