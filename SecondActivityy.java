package com.example.scorecount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;


public class SecondActivityy extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_PERMISSION_PHONE = 1;
    public static final int REQUEST_PERMISSION_MESSAGE = 2;
    private TextView tv_winner;
    private EditText ph_number;
    private Button call_button;
    private Button message_button;
    private Button map_button;
    private String winner;
    private int scoreDifference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activityy);
        tv_winner = findViewById(R.id.winner_text);
        ph_number = findViewById(R.id.phone_number);
        call_button = findViewById(R.id.call_someone);
        message_button = findViewById(R.id.message_someone);
        map_button = findViewById(R.id.map_location);
        callListener();
        Intent intent = getIntent();
        int homeCounter = intent.getIntExtra(MainActivity.EXTRA_HOME,0);
        int awayCounter = intent.getIntExtra(MainActivity.EXTRA_AWAY,0);
         winner = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

         scoreDifference = (Math.max(homeCounter,awayCounter)) - Math.min(homeCounter,awayCounter);

        tv_winner.setText(winner + " won by " + scoreDifference);
    }
    private void callListener() {
        call_button.setOnClickListener(this);
        message_button.setOnClickListener(this);
        map_button.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.call_someone:
                phone();
                break;
            case R.id.message_someone:
                message();
                break;
            case  R.id.map_location:
                location();
                break;
        }



    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSION_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    phone();
                }

                break;
            case REQUEST_PERMISSION_MESSAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    message();
                }
                break;
        }


    }
    private void phone()   {
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.parse("tel: " + ph_number.getText().toString() );
        phoneIntent.setData(uri);
        if(phoneIntent.resolveActivity(getPackageManager()) !=null)
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String [] {Manifest.permission.CALL_PHONE},REQUEST_PERMISSION_PHONE);
            }
            else {
                startActivity(phoneIntent);
            }

}
    private void message() {
        String text = "sms:" + ph_number.getText().toString();
        String winMessage = winner + " won by " + scoreDifference;
        Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(text));
        messageIntent.putExtra("sms_body",winMessage );
        startActivity(messageIntent);
        if(messageIntent.resolveActivity(getPackageManager()) != null){
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS}, REQUEST_PERMISSION_MESSAGE);
            }
            else{
                startActivity(messageIntent);
            }
        }
    }

    private void location() {
        Uri search = Uri.parse("geo:0,0?q=baseball near me");

        Intent locationIntent = new Intent(Intent.ACTION_VIEW, search);
        startActivity(locationIntent);

    }
}