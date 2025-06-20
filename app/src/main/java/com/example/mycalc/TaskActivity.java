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

import java.util.ArrayList;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    public EditText NachTask, KonTask, HistTask;
    public Button BtnClear, BtnHist, BtnTask1, BtnTask2, BtnTask4, BtnTask6;
    public SharedPreferences Shared_Task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NachTask = findViewById(R.id.nach_task);
        KonTask = findViewById(R.id.kon_task);
        HistTask = findViewById(R.id.hist_task);

        BtnClear = findViewById(R.id.clear);
        BtnHist = findViewById(R.id.history);
        BtnTask1 = findViewById(R.id.task1);
        BtnTask2 = findViewById(R.id.task2);
        BtnTask4 = findViewById(R.id.task4);
        BtnTask6 = findViewById(R.id.task6);

        Shared_Task = getSharedPreferences("Task", MODE_PRIVATE);

        BtnTask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZuckermanNumbers();
            }
        });

        BtnTask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NivenNumbers();
            }
        });

        BtnTask4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SomeNumbers();
            }
        });

        BtnTask6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KitNumbers();
            }
        });

        BtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Clear();
            }
        });

        BtnHist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                History();
            }
        });

    }

    private void ZuckermanNumbers() {
        String Nach1 = NachTask.getText().toString();
        String Kon1 = KonTask.getText().toString();

        String res = "";

        if (Nach1.isEmpty() || Kon1.isEmpty()) {
            Toast.makeText(this, "Введите числа", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int nach = Integer.parseInt(Nach1);
            int kon = Integer.parseInt(Kon1);

            if (nach < 1 || kon < 1) {
                Toast.makeText(this, "Числа должны быть больше нуля", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nach > kon) {
                Toast.makeText(this, "Начальное число должно быть меньше конечного", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nach > 20 || kon > 20) {
                Toast.makeText(this, "Числа не должны привышать 20 символов", Toast.LENGTH_SHORT).show();
            }

            for (int i = nach; i <= kon; i++) {
                if (CheckZukNum(i)) {
                    res += i + " ";
                }
            }

            if (res.length() > 0) {
                HistTask.append("Числа Цукермана:\n" + res + "\n\n");
            }
            else {
                Toast.makeText(this, "Числа не найдены", Toast.LENGTH_SHORT).show();
            }
            SharedPreferences.Editor edit = Shared_Task.edit();
            edit.putString("res1", res);
            edit.apply();
        }
        catch (NumberFormatException ex) {
            Toast.makeText(this, "Ошибка ввода чисел", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean CheckZukNum(int i) {
        int proizv = 1;
        int copyi = i;

        while (copyi > 0) {
            int dig = copyi % 10;

            if (dig == 0) {
                return false;
            }

            proizv *= dig;
            copyi /= 10;
        }

        return i % proizv == 0;
    }

    /////////////////////////////

    private void NivenNumbers() {
        String Nach2 = NachTask.getText().toString();
        String Kon2 = KonTask.getText().toString();

        String res = "";

        if (Nach2.isEmpty() || Kon2.isEmpty()) {
            Toast.makeText(this, "Введите числа", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int nach = Integer.parseInt(Nach2);
            int kon = Integer.parseInt(Kon2);

            if (nach < 1 || kon < 1) {
                Toast.makeText(this, "Числа должны быть больше нуля", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nach > kon) {
                Toast.makeText(this, "Начальное число должно быть меньше конечного", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nach > 20 || kon > 20) {
                Toast.makeText(this, "Числа не должны привышать 20 символов", Toast.LENGTH_SHORT).show();
            }

            for (int i = nach; i <= kon; i++) {
                if (CheckNivNum(i)) {
                    res += i + " ";
                }
            }

            if (res.length() > 0) {
                HistTask.append("Числа Нивена:\n" + res + "\n\n");
            }
            else {
                Toast.makeText(this, "Числа не найдены", Toast.LENGTH_SHORT).show();
            }
            SharedPreferences.Editor edit = Shared_Task.edit();
            edit.putString("res2", res);
            edit.apply();
        }
        catch (NumberFormatException ex) {
            Toast.makeText(this, "Ошибка ввода чисел", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean CheckNivNum(int i) {
        int sum = 0;
        int copyi = i;

        while (copyi > 0) {
            int dig = copyi % 10;
            sum += dig;
            copyi /= 10;
        }

        return i % sum == 0;
    }

    /////////////////////////////

    private void SomeNumbers() {
        StringBuilder res = new StringBuilder();
        res.append("Число 73 — лучшее число потому что:\n");

        if (SomeNumNat(73) && SomeNumPos(73) == 21) {
            res.append("73 — простое число и находится на 21 позиции\n");
        } else {
            res.append("73 не соответсвует первому условию\n");
        }

        int mir_73 = 37;
        if (SomeNumNat(mir_73) && SomeNumPos(mir_73) == 12) {
            res.append("Обратное число ").append(mir_73).append(" тоже простое и находится на 12 позиции\n");
        } else {
            res.append("Обратное число ").append(mir_73).append(" не соответствует второму условию\n");
        }

        int proizv = 7 * 3;
        if (proizv == 21) {
            res.append("Произведение цифр 7 и 3 равно позиции в списке простых чисел\n");
        } else {
            res.append("Произведение цифр не равно 21\n");
        }

        String bin = Integer.toBinaryString(73);
        if (bin.equals(new StringBuilder(bin).reverse().toString())) {
            res.append("В двоичной системе  ").append(bin).append(" — это палиндром.");
        } else {
            res.append("В двоичной системе ").append(bin).append(" не является палиндромом.");
        }

        HistTask.append(res.toString() + "\n\n");

        SharedPreferences.Editor edit = Shared_Task.edit();
        edit.putString("res4", res.toString());
        edit.apply();
    }

    private boolean SomeNumNat(int n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
    }

    private int SomeNumPos(int pos) {
        int seat = 0;
        int num = 2;

        while (num <= 1000) {
            if (SomeNumNat(num)) {
                seat++;
            }
            if (num == pos) {
                return seat;
            }
            num++;
        }

        return 0;
    }

    /////////////////////////////

    private void KitNumbers() {
        String Nach6 = NachTask.getText().toString();
        String Kon6 = KonTask.getText().toString();

        if (Nach6.isEmpty() || Kon6.isEmpty()) {
            Toast.makeText(this, "Введите числа", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int nach = Integer.parseInt(Nach6);
            int kon = Integer.parseInt(Kon6);

            if (nach < 1 || kon < 1) {
                Toast.makeText(this, "Числа должны быть больше нуля", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nach > kon) {
                Toast.makeText(this, "Начальное число должно быть меньше конечного", Toast.LENGTH_SHORT).show();
                return;
            }

            if (nach > 20 || kon > 20) {
                Toast.makeText(this, "Числа не должны привышать 20 символов", Toast.LENGTH_SHORT).show();
            }

            List<Integer> KitList = new ArrayList<>();

            for (int i = nach; i <= kon; i++) {
                if (CheckKitNum(i)) {
                    KitList.add(i);
                }
            }

            StringBuilder res = new StringBuilder("Найденные числа Кита:\n");
            if (KitList.isEmpty()) {
                HistTask.append("Чисел Кита не найдено" + "\n\n");
            } else {
                for (int num : KitList) {
                    res.append(num).append(" ");
                }
                HistTask.append(res.toString() + "\n\n");
            }
            SharedPreferences.Editor edit = Shared_Task.edit();
            edit.putString("res6", res.toString());
            edit.apply();
        }
        catch (NumberFormatException ex) {
            Toast.makeText(this, "Ошибка ввода чисел", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean CheckKitNum(int num) {
        if (num < 10) return false;

        List<Integer> dig = new ArrayList<>();
        int buf = num;

        while (buf > 0) {
            dig.add(0, buf % 10);
            buf /= 10;
        }

        int n = dig.size();

        while (true) {
            int sum = 0;
            for (int d : dig) {
                sum += d;
            }

            if (sum == num) {
                return true;
            }
            if (sum > num) {
                return false;
            }

            dig.remove(0);
            dig.add(sum);
        }
    }

    /////////////////////////////

    private void Clear() {
        NachTask.setText("");
        KonTask.setText("");
    }

    /////////////////////////////

    private void History() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
}