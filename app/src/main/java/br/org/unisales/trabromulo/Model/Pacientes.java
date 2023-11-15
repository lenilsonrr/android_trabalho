package br.org.unisales.trabromulo.Model;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import javax.validation.constraints.NotNull;


@Indices(value = {

        @Index(value = "cpf", type = IndexType.Unique)
})

public class Pacientes {

    @Id
    public NitriteId id;

    private String nome;

    private String cpf;


    private String celular;

    private Medicos medicos;
    private String nomeMedico;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
        this.nomeMedico =
                (this.medicos != null)
                        ? this.nome + ":" + this.medicos.id.getIdValue()
                        : this.nome + ":";

    }
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


    public Medicos getMedicos() {
        return medicos;
    }
    public void setMedicos(Medicos medicos) {
        this.medicos = medicos;
        this.nomeMedico = this.nome + ":" + this.medicos.id.getIdValue();
    }

    @Override
    public String toString() {return this.nome +" " +this.cpf+" "+this.celular+ " [" + medicos.nome +" "+medicos.especialidade+"] ";}
}
