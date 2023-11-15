package br.org.unisales.trabromulo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.org.unisales.trabromulo.database.BancoDados;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BancoDados.init(getFilesDir().getPath()+ "/banco.db01");

    }

    public void abreMedicos(View view){
        startActivity(new Intent(this, MedicosActivity.class));
    }

    public void abrePacientes(View view){ startActivity(new Intent(this, PacientesActivity.class));}
}