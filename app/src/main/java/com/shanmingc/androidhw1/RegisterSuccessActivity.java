package com.shanmingc.androidhw1;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static com.shanmingc.androidhw1.MainActivity.*;

public class RegisterSuccessActivity extends AppCompatActivity {

    private String phoneNumber;
    private String city;
    private String[] hobbies;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_success);

        Intent intent = getIntent();

        phoneNumber = intent.getStringExtra(PHONE_NUMBER_KEY);
        city = intent.getStringExtra(CITY_KEY);
        hobbies = intent.getStringArrayExtra(HOBBIES_KEY);
        sex = intent.getStringExtra(SEX_KEY);


        getSupportActionBar().setTitle(phoneNumber);

        ImageView sexIcon = findViewById(R.id.sexIcon);
        sexIcon.setImageResource((sex.equals("male")? R.drawable.male : R.drawable.female));

        TextView cityInfo = findViewById(R.id.cityInfo);
        cityInfo.setText("来自"+ city);
    }
}
