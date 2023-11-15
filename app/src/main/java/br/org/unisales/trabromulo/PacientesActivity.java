package br.org.unisales.trabromulo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import br.org.unisales.trabromulo.Model.Medicos;
import br.org.unisales.trabromulo.Model.Pacientes;
import br.org.unisales.trabromulo.database.BancoDados;

public class PacientesActivity extends AppCompatActivity {

    Pacientes pacientes = new Pacientes();

    EditText inNome;

    EditText inCpf;

    EditText inCelular;

    Spinner spinnerMedicos;
    ListView listaPacientes;

    ArrayAdapter<Medicos> adapterMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes);
        inNome = findViewById(R.id.inNomePaciente);
        inCpf = findViewById(R.id.inCpfPaciente);
        inCelular = findViewById(R.id.inCelularPaciente);
        spinnerMedicos = findViewById(R.id.spinnerMedicos);
        listaPacientes = findViewById(R.id.listaViewPacientes);
        adapterMedicos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
         BancoDados.dbMedicos.find().toList()    );
        spinnerMedicos.setAdapter(adapterMedicos);
    }

    public void getListaPacientes() {
        System.out.println("Aqui");
        List<Pacientes> lista = BancoDados.dbPacintes.find().toList();
        final ArrayAdapter<Pacientes> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listaPacientes.setAdapter(arrayAdapter);
        listaPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pacientes = BancoDados.dbPacintes.getById(arrayAdapter.getItem(i).id);
                inNome.setText(pacientes.getNome());
                inCpf.setText(pacientes.getCpf());
                inCelular.setText(pacientes.getCelular());

            int pos = adapterMedicos.getPosition(pacientes.getMedicos());
            spinnerMedicos.setSelection(pos);
                inNome.requestFocus();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getListaPacientes();
    }


    public void cancelar(View view) {
        this.pacientes = new Pacientes();
        inNome.setText("");
        inCpf.setText("");
        inCelular.setText("");
        spinnerMedicos.setSelection(0);
        inNome.requestFocus();
    }


    public void salvar(View view) {
        try {
            this.pacientes.setNome("" + inNome.getText());
            this.pacientes.setCpf(""+inCpf.getText());
            this.pacientes.setCelular(""+inCelular.getText());

          this.pacientes.setMedicos((Medicos) spinnerMedicos.getSelectedItem());
            if (this.pacientes.id == null) {
                BancoDados.dbPacintes.insert(this.pacientes);
            } else {
                BancoDados.dbPacintes.update(this.pacientes);
            }
        } catch (Exception ex) {
            new AlertDialog.Builder(this)
                    .setMessage(ex.getMessage())
                    .setPositiveButton(android.R.string.yes, null)
                    .show();
        }
        this.pacientes = new Pacientes();
        inNome.setText("");
        inCpf.setText("");
        inCelular.setText("");
       spinnerMedicos.setSelection(0);
        getListaPacientes();
    }


    public void excluir(View view) {
        if (this.pacientes.id != null) {
            BancoDados.dbPacintes.remove(this.pacientes);
        }
        this.pacientes = new Pacientes();
        inNome.setText("");
        inCpf.setText("");
        inCelular.setText("");
        spinnerMedicos.setSelection(0);
        getListaPacientes();
    }

}