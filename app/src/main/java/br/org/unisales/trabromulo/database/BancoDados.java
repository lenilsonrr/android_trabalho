package br.org.unisales.trabromulo.database;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import br.org.unisales.trabromulo.Model.Medicos;
import br.org.unisales.trabromulo.Model.Pacientes;

public class BancoDados {

    public static ObjectRepository<Medicos> dbMedicos;
    public static ObjectRepository<Pacientes> dbPacintes;


    public static void init(String file) {
        Nitrite db = Nitrite.builder()
                .compressed()
                .filePath(file)
                .openOrCreate("root", "salesiano");


        dbMedicos = db.getRepository(Medicos.class);
        dbPacintes = db.getRepository(Pacientes.class);
    }
}
