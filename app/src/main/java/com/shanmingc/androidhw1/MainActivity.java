package com.shanmingc.androidhw1;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatRadioButton;

import java.util.*;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private List<String> cityList;

    private String sex;

    private Set<String> hobbies;

    private String city;

    private static final String TAG = "MainActivity";

    private String phoneNumber;

    private String password;

    static final String CITY_KEY = "city";

    static final String SEX_KEY = "sex";

    static final String HOBBIES_KEY = "hobbies";

    static final String PHONE_NUMBER_KEY = "phoneNumber";


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.read:
                if(!hobbies.contains(getString(R.string.read)))
                    hobbies.add(getString(R.string.read));
                else hobbies.remove(getString(R.string.read));
                Log.d(TAG, "checked read");
                break;
            case R.id.playBall:
                if(!hobbies.contains(getString(R.string.playBall)))
                    hobbies.add(getString(R.string.playBall));
                else hobbies.remove(getString(R.string.playBall));
                Log.d(TAG, "checked play ball");
                break;
            case R.id.listenMusic:
                if(!hobbies.contains(getString(R.string.listenMusic)))
                    hobbies.add(getString(R.string.listenMusic));
                else hobbies.remove(getString(R.string.listenMusic));
                Log.d(TAG, "checked listen music");
                break;
            default:
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.male:
                sex = "male";
                Log.d(TAG, "checked male");
                break;
            case R.id.female:
                sex = "female";
                Log.d(TAG, "checked female");
                break;
            default:
        }
    }

    private void init() {
        cityList = Arrays.asList(
                getString(R.string.Beijing),
                getString(R.string.Shanghai),
                getString(R.string.Wuhan),
                getString(R.string.Nanjing),
                getString(R.string.Nanchang));
        hobbies = new LinkedHashSet<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.title);

        init();

        Spinner citySpinner = findViewById(R.id.citySpinner);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cityList);
        citySpinner.setAdapter(adapter);
        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = cityList.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        RadioGroup radioGroup = findViewById(R.id.sexRadioGroup);
        radioGroup.setOnCheckedChangeListener(this);

        CheckBox checkRead = findViewById(R.id.read);
        CheckBox checkPlayBall = findViewById(R.id.playBall);
        CheckBox checkListenMusic = findViewById(R.id.listenMusic);

        checkRead.setOnClickListener(this);
        checkPlayBall.setOnClickListener(this);
        checkListenMusic.setOnClickListener(this);

        Button register = findViewById(R.id.registerButton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, R.string.registerInfo, Toast.LENGTH_SHORT).show();
                if(phoneNumber == null || phoneNumber.length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.phoneNumberNotRegInfo, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password == null || password.length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.passwordNotRegInfo, Toast.LENGTH_SHORT).show();
                    return;
                }
                if(sex == null) {
                    Toast.makeText(MainActivity.this, R.string.sexNotRegInfo, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, RegisterSuccessActivity.class);
                intent.putExtra(SEX_KEY, sex);
                intent.putExtra(CITY_KEY, city);
                intent.putExtra(PHONE_NUMBER_KEY, phoneNumber);
                intent.putExtra(HOBBIES_KEY, hobbies.toArray());
                startActivity(intent);
            }
        });

        EditText phoneNumberEdit = findViewById(R.id.phoneNumberEdit);
        EditText passwordEdit = findViewById(R.id.passwordEdit);
        phoneNumberEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneNumber = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
