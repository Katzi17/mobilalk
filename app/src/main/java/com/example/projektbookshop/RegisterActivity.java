package com.example.projektbookshop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText userNameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    EditText passwordAgainEditText;
    EditText phoneNumberEditText;
    EditText addressEditText;

    RadioGroup accountTypeGroup;


    private FirebaseAuth mAuth;

    private static final String TAG= RegisterActivity.class.getName();
    private static final String PREF_KEY = MainActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;
    private SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userNameEditText = findViewById(R.id.userNameEditText);
        emailEditText = findViewById(R.id.userEmailEditText);
        passwordEditText = findViewById(R.id.userPasswordEditText);
        passwordAgainEditText = findViewById(R.id.userPasswordAgainEditText);
        phoneNumberEditText = findViewById(R.id.userPhoneNumberEditText);
        addressEditText = findViewById(R.id.userAddressEditText);
        accountTypeGroup = findViewById(R.id.accountTypeGroup);
        accountTypeGroup.check(R.id.buyerRadioButton);

        preferences = getSharedPreferences(PREF_KEY,MODE_PRIVATE);


        String userName = preferences.getString("userName","");
        String password = preferences.getString("password", "");

        userNameEditText.setText(userName);
        passwordEditText.setText(password);
        passwordAgainEditText.setText(password);
        mAuth = FirebaseAuth.getInstance();

    }

    public void register(View view){
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String email= emailEditText.getText().toString();
        String passwordAgain = passwordAgainEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();
        String address = addressEditText.getText().toString();

       if (!password.equals(passwordAgain)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Error")
                    .setMessage("Nem egyenlő a jelszó és az ellenőrző jelszó!")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        } else if(password.length() < 6){
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Error")
                    .setMessage("A jelszónak legalább 6 karakter hosszúnak kell lennie!")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }else if (!isValidEmail(email)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
            builder.setTitle("Error")
                    .setMessage("Nem megfelelő az email formátum!")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }


        int checkedId = accountTypeGroup.getCheckedRadioButtonId();
        RadioButton radioButton = accountTypeGroup.findViewById(checkedId);
        String accountType = radioButton.getText().toString();

        startShopping();

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Sikeres regisztráció!");
                    startShopping();
                }else{
                    Log.d(TAG, "Sikertelen regisztráció!");

                }
            }
        });

    }


    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public void cancel(View view) {
        finish();
    }

    private void startShopping(/**/){
        Intent intent = new Intent(this, ShopListActivity.class);
        //intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }


}