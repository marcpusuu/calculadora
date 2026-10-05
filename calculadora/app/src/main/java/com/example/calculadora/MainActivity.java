package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView pantalla;
    Switch radians;

    double primer = 0;
    String operacio = "";
    boolean nouNumero = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pantalla = findViewById(R.id.pantalla);
        radians = findViewById(R.id.radians);
    }

    public void numero(View v) {
        Button b = (Button) v;
        String num = b.getText().toString();

        if (nouNumero || pantalla.getText().toString().equals("0")) {
            pantalla.setText(num);
            nouNumero = false;
        } else {
            pantalla.append(num);
        }
    }

    public void operador(View v) {
        Button b = (Button) v;

        if (!operacio.equals("") && !nouNumero) {
            calcula();
        } else {
            primer = Double.parseDouble(pantalla.getText().toString());
        }

        operacio = b.getText().toString();
        nouNumero = true;
    }

    public void igual(View v) {
        if (!operacio.equals("")) {
            calcula();
            operacio = "";
        }
        nouNumero = true;
    }

    public void esborra(View v) {
        pantalla.setText("0");
        primer = 0;
        operacio = "";
        nouNumero = true;
    }

    public void trigo(View v) {
        Button b = (Button) v;
        double x = Double.parseDouble(pantalla.getText().toString());

        if (!radians.isChecked()) {
            x = Math.toRadians(x);
        }

        double res;
        String funcio = b.getText().toString();

        if (funcio.equals("sin")) {
            res = Math.sin(x);
        } else if (funcio.equals("cos")) {
            res = Math.cos(x);
        } else {
            res = Math.tan(x);
        }

        mostra(res);
        nouNumero = true;
    }

    private void calcula() {
        double segon = Double.parseDouble(pantalla.getText().toString());
        double res = 0;

        if (operacio.equals("+")) {
            res = primer + segon;
        } else if (operacio.equals("-")) {
            res = primer - segon;
        } else if (operacio.equals("*")) {
            res = primer * segon;
        } else if (operacio.equals("/")) {
            res = primer / segon;
        }

        primer = res;
        mostra(res);
    }

    private void mostra(double n) {
        if (n == (long) n) {
            pantalla.setText(String.valueOf((long) n));
        } else {
            pantalla.setText(String.valueOf(n));
        }
    }
}
