package br.org.unisales.trabromulo.Model;


import org.dizitart.no2.IndexType;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.util.Objects;

import javax.validation.constraints.NotNull;

@Indices(value = {
        @Index(value = "crm", type = IndexType.Unique)
})
public class Medicos {
    @Id
    public NitriteId id;
    @NotNull
    public String nome;
    @NotNull
    public String crm;

    @NotNull
    public String celular;

    @NotNull
    public String especialidade;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicos medicos = (Medicos) o;
        return Objects.equals(id, medicos.id);
    }

    public String mostrarNaLista(){
        return this.nome+" "+this.crm+" "+this.celular+" "+this.especialidade;
    }

    @Override
    public String toString(){
        return this.nome+" "+this.especialidade;
    }

}
