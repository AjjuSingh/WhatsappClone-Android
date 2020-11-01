package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainPageActivity extends AppCompatActivity {
    private Button mLogout, mFindUser;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mLogout = findViewById(R.id.logout);
        mFindUser = findViewById(R.id.findUser);

        mFindUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), FindUserActivity.class));
            }
        });

        getPermissions();


        mLogout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return;
            }
        });
    }

    private void getPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Check for permission before seeing contact details
            requestPermissions(new String[] {Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS}, 1);
        }
    }
}