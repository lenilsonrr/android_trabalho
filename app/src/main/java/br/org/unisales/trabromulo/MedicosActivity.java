package br.org.unisales.trabromulo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import br.org.unisales.trabromulo.Model.Medicos;
import br.org.unisales.trabromulo.database.BancoDados;

public class MedicosActivity extends AppCompatActivity {

    Medicos medicos = new Medicos();

    EditText inNome;

    EditText inCrm;

    EditText inCelular;

    EditText inEspecialidae;

    ListView listaMedicos;

    ArrayAdapter<Medicos> adapterMedicos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicos);
        inNome = findViewById(R.id.inNomeMedico);
        inCrm = findViewById(R.id.inCrmMedico);
        inCelular = findViewById(R.id.inCelularMedico);
        inEspecialidae = findViewById(R.id.inEspecialidadeMedico);
        listaMedicos = findViewById(R.id.listaMedicos);
        adapterMedicos = new MedicoAdapter(this, BancoDados.dbMedicos.find().toList());
        listaMedicos.setAdapter(adapterMedicos);
    }

    public void salvarM(View view) {
        try {
            this.medicos.nome = "" + inNome.getText();
            this.medicos.crm = "" + inCrm.getText();
            this.medicos.celular = "" + inCelular.getText();
            this.medicos.especialidade = "" + inEspecialidae.getText();
            if (this.medicos.id == null) {
                BancoDados.dbMedicos.insert(this.medicos);
            } else {
                BancoDados.dbMedicos.update(this.medicos);
            }
        } catch (Exception ex) {
            new AlertDialog.Builder(this)
                    .setMessage(ex.getMessage())
                    .setPositiveButton(android.R.string.yes, null)
                    .show();
        }
        this.medicos = new Medicos();
        this.inNome.setText("");
        this.inCrm.setText("");
        this.inCelular.setText("");
        this.inEspecialidae.setText("");
        getListaMedicos();
    }

    public void getListaMedicos() {
        List<Medicos> lista = BancoDados.dbMedicos.find().toList();
        adapterMedicos.clear();
        adapterMedicos.addAll(lista);
        adapterMedicos.notifyDataSetChanged();
        listaMedicos.setAdapter(adapterMedicos);
        listaMedicos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                medicos = BancoDados.dbMedicos.getById(adapterMedicos.getItem(i).id);
                inNome.setText(medicos.nome);
                inCrm.setText(medicos.crm);
                inCelular.setText(medicos.celular);
                inEspecialidae.setText(medicos.especialidade);
                inNome.requestFocus();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListaMedicos();
    }


    public void cancelarM(View view) {
        this.medicos = new Medicos();
        inNome.setText("");
        inCrm.setText("");
        inCelular.setText("");
        inEspecialidae.setText("");
        inNome.requestFocus();
    }


    public void excluirM(View view) {
        if (this.medicos.id != null) {
            BancoDados.dbMedicos.remove(this.medicos);
        }
        this.medicos = new Medicos();
        this.inNome.setText("");
        this.inCrm.setText("");
        this.inCelular.setText("");
        this.inEspecialidae.setText("");
        getListaMedicos();
    }

}
