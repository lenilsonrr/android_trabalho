package br.org.unisales.trabromulo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.org.unisales.trabromulo.Model.Medicos;

public class MedicoAdapter extends ArrayAdapter<Medicos> {

    public MedicoAdapter(Context context, List<Medicos> medicosList) {
        super(context, 0, medicosList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Medicos medico = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(medico.mostrarNaLista());

        return convertView;
    }
}

