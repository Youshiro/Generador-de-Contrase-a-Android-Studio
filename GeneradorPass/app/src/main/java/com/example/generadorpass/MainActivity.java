package com.example.generadorpass;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button generar;
    TextView etiqueta;
    CheckBox mayuscula;
    CheckBox simbolo;
    String password;
    Button sumar;
    Button restar;
    int contador = 8;
    TextView numero;
    Button limpiar;
    TextView mantenPulsado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generar = (Button) findViewById(R.id.button);
        sumar = (Button) findViewById(R.id.button2);
        restar = (Button) findViewById(R.id.button3);
        etiqueta = (TextView) findViewById(R.id.textView2);
        mayuscula = (CheckBox) findViewById(R.id.ck1);
        simbolo = (CheckBox) findViewById(R.id.ck2);
        numero = (TextView) findViewById(R.id.textView3);
        limpiar = (Button) findViewById(R.id.button4);
        mantenPulsado = (TextView) findViewById(R.id.textView4);

        numero.setText(Integer.toString(contador));


    }

    public void generar(View view) {

        password = "";
        limpiar.setEnabled(true);
        generar.setEnabled(false);


        for (int i = 0; i < contador; i++) {

            int random = (int) (Math.random() * (4 + 1)); //ALEATORIO 1 Ó 2.

            if (simbolo.isChecked() && random == 1) {

                password += (char) (Math.random() * (33 - 43) + 43); //SIMBOLOS

            } else if (mayuscula.isChecked() && random == 1) {

                password += (char) (Math.random() * (65 - 91) + 91); //LETRAS MAYUSCULAS ASCII

            } else if (simbolo.isChecked() && random == 2) {

                password += (char) (Math.random() * (33 - 43) + 43); //SIMBOLOS

            } else if (mayuscula.isChecked() && random == 2) {

                password += (char) (Math.random() * (65 - 91) + 91); //LETRAS MAYUSCULAS ASCII

            } else if (simbolo.isChecked() && mayuscula.isChecked() && random == 1) {

                password += (char) (Math.random() * (33 - 43) + 43); //SIMBOLOS

            } else if (mayuscula.isChecked() && simbolo.isChecked() && random == 3) {

                password += (char) (Math.random() * (65 - 91) + 91); //LETRAS MAYUSCULAS ASCII

            } else {

                password += (char) (Math.random() * (123 - 97) + 97); //LETRAS MINUSCULAS ASCII
            }

        }
        etiqueta.setText(password);
    }

    public void sumar(View view) {

        if (contador >= 8 && contador < 20) {

                contador ++;

                numero.setText(Integer.toString(contador));

        }
        //Toast.makeText(this,"Botón sumar", Toast.LENGTH_SHORT).show();
    }


    public void restar(View view) {

        if (contador > 8 && contador <= 20) {

            contador --;

            numero.setText(Integer.toString(contador));

        }
    }

    public void limpiar(View view) {

        contador = 8;
        limpiar.setEnabled(false);
        generar.setEnabled(true);

        numero.setText(Integer.toString(contador));
        etiqueta.setText("");
        mayuscula.setChecked(false);
        simbolo.setChecked(false);

    }

    public void copiar(View view) {

        if(etiqueta.getText().equals("")) {

            Toast.makeText(this, "No hay contraseña para copiar", Toast.LENGTH_SHORT).show();

        } else {

            etiqueta.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    if(!etiqueta.getText().equals("")) {
                        String text = etiqueta.getText().toString();
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("", text);
                        clipboard.setPrimaryClip(clip);

                        Toast.makeText(getApplicationContext(), "Contraseña copiada al portapapeles", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
            });

        }
    }
}
