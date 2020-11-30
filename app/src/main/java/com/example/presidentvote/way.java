package com.example.presidentvote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;

public class way extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private GoogleApiClient googleApiClient;
    private TextView tv_result;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way);
        setTitle(R.string.app_name2);


        Intent intent = getIntent();
        String nickName = intent.getStringExtra("nickName");
        String photoUri = intent.getStringExtra("photoUri");

        tv_result = findViewById(R.id.tv_result);
        tv_result.setText(nickName);

        imageView = findViewById(R.id.imageView);
        Glide.with(this).load(photoUri).into(imageView);

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.way_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();

       if(id == R.id.action_btn1){
           Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
               @Override
               public void onResult(@NonNull Status status) {
                   FirebaseAuth.getInstance().signOut();
                   Toast.makeText(way.this, "로그아웃 하였습니다.", Toast.LENGTH_LONG).show();
               }
           });


           Intent intent = new Intent(this, Login.class);
           startActivity(intent);
           finish();
           return true;
       }
       return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void managerView(View view) {
        LinearLayout linear = (LinearLayout) View.inflate(this, R.layout.activity_alert_dialog, null);
        new AlertDialog.Builder(this).setView(linear)
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText passward = (EditText)linear.findViewById(R.id.editps);
                String value = passward.getText().toString();
                if(value.equals("123456")) {
                    Toast.makeText(way.this, "인증성공", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Manager.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(way.this, "틀렸습니다", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        }).show();
    }
}