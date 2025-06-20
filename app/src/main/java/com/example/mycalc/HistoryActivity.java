package com.example.mycalc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HistoryActivity extends AppCompatActivity {

    public EditText HistPanel;
    public Button BtnBack;
    public SharedPreferences Shared_Task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        HistPanel = findViewById(R.id.hist_panel);

        BtnBack = findViewById(R.id.back);

        Shared_Task = getSharedPreferences("Task", MODE_PRIVATE);

        StringBuilder histpanel = new StringBuilder();
        if (!Shared_Task.getString("res1", "").isEmpty()) {
            histpanel.append("Числа Цукермана:\n").append(Shared_Task.getString("res1", "")).append("\n");
        }
        if (!Shared_Task.getString("res2", "").isEmpty()) {
            histpanel.append("Числа Нивена:\n").append(Shared_Task.getString("res2", "")).append("\n");
        }
        if (!Shared_Task.getString("res4", "").isEmpty()) {
            histpanel.append(Shared_Task.getString("res4", "")).append("\n");
        }
        if (!Shared_Task.getString("res6", "").isEmpty()) {
            histpanel.append("Числа Кита:\n").append(Shared_Task.getString("res6", "")).append("\n");
        }

        HistPanel.setText(histpanel);

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });
    }

    private void Back() {
        finish();
    }

}