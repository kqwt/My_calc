package com.example.mycalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    public EditText UsernameReg, EmailReg, PasswordReg, RepPassReg;
    public Button BtnZareg, BtnLog;
    public SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.register), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        UsernameReg = findViewById(R.id.use_reg);
        EmailReg = findViewById(R.id.em_reg);
        PasswordReg = findViewById(R.id.pas_reg);
        RepPassReg = findViewById(R.id.reppas_reg);

        shared = getSharedPreferences("data", MODE_PRIVATE);

        BtnZareg = findViewById(R.id.but_zareg);
        BtnLog = findViewById(R.id.but_log);

        BtnZareg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registration_acc();
            }
        });

        BtnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log_acc();
            }
        });

    }

    private void Registration_acc() {
        String Username = UsernameReg.getText().toString();
        String Email = EmailReg.getText().toString();
        String Password = PasswordReg.getText().toString();
        String RepPass = RepPassReg.getText().toString();

        if (Username.isEmpty() || Email.isEmpty() || Password.isEmpty() || RepPass.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Username.length() > 20) {
            Toast.makeText(this, "Никнейм не должен превышать 20 символов", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!(Email != null && Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$").matcher(Email).matches())) {
            Toast.makeText(this, "Email не соответствует шаблону", Toast.LENGTH_SHORT).show();
            return;
        }

        if (Password.length() > 8) {
            Toast.makeText(this, "Пароль не должен превышать 8 символов", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Password.equals(RepPass)) {
            Toast.makeText(this, "Пароли не соответствуют", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor edit = shared.edit();
        edit.putString("username", Username);
        edit.putString("email", Email);
        edit.putString("password", Password);
        edit.apply();

        Toast.makeText(this, "Аккаунт зарегистрирован", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void Log_acc() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

}