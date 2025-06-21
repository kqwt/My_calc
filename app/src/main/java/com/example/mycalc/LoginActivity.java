package com.example.mycalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    public EditText EmailLog, PasswordLog;
    public Button BtnLogin, BtnReg;
    public SharedPreferences shared, Shared_Task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EmailLog = findViewById(R.id.em_log);
        PasswordLog = findViewById(R.id.pas_log);

        shared = getSharedPreferences("data", MODE_PRIVATE);
        Shared_Task = getSharedPreferences("Task", MODE_PRIVATE);

        SharedPreferences.Editor EdTas = Shared_Task.edit();
        EdTas.clear();
        EdTas.apply();

        BtnLogin = findViewById(R.id.but_login);
        BtnReg = findViewById(R.id.but_reg);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_acc();
            }
        });

        BtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register_acc();
            }
        });
    }

    private void Login_acc() {
        String Email = EmailLog.getText().toString();
        String Password = PasswordLog.getText().toString();

        String RegUsername = shared.getString("username", "");
        String RegEmail = shared.getString("email", "");
        String RegPassword = shared.getString("password", "");

        int buf = 0;

        if (Email.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }

        if ((Email.equals("admin") || Email.equals("admin@example.com")) && Password.equals("admin")) {
            Toast.makeText(this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, TaskActivity.class);
            startActivity(intent);
            buf = 1;
        }

        if (buf == 0) {
            if ((Email.equals(RegUsername) || Email.equals(RegEmail)) && Password.equals(RegPassword)) {
                Toast.makeText(this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, TaskActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Данные введены неверно", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void Register_acc() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}