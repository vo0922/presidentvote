package com.example.presidentvote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Manager extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    public final static int REQUES_PHOTO_CODE = 1;
    ImageView iv_photo;
    Uri photoUri;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("candidate");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        setTitle("등록");


        iv_photo = (ImageView)findViewById(R.id.photo);
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
        getMenuInflater().inflate(R.menu.manager_menu, menu);
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
                    Toast.makeText(Manager.this, "로그아웃 하였습니다.", Toast.LENGTH_LONG).show();
                }
            });

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
            finish();
            return true;
        } else if(id == R.id.action_btn2) {
            Intent intent = new Intent(this, way.class);
            startActivity(intent);
            finish();
            return true;
        } else if(id == R.id.action_btn3) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void selectPhoto(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUES_PHOTO_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUES_PHOTO_CODE) {
            if (resultCode == RESULT_OK) {
                photoUri = data.getData();
                iv_photo.setImageURI(photoUri);
            } else {
                Toast.makeText(this, "사진 선택 에러!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void register(View view) {
        EditText et_name = (EditText)findViewById(R.id.name);
        String str_name = et_name.getText().toString();

        RadioGroup rg_gender = (RadioGroup)findViewById(R.id.gender);
        RadioButton rb_gender;
        String str_gender = "";
        if(rg_gender.getCheckedRadioButtonId() == R.id.male) {
            rb_gender = (RadioButton)findViewById(R.id.male);
            str_gender = rb_gender.getText().toString();
        }
        if(rg_gender.getCheckedRadioButtonId() == R.id.female) {
            rb_gender = (RadioButton)findViewById(R.id.female);
            str_gender = rb_gender.getText().toString();
        }

        EditText et_cs = (EditText)findViewById(R.id.cs);
        String str_cs = et_cs.getText().toString();

        myRef.setValue(str_name);
        myRef.child(str_name).child("class").setValue(str_cs);
        myRef.child(str_name).child("gender").setValue(str_gender);
        //myRef.child(str_name).child("image").setValue();
    }
}