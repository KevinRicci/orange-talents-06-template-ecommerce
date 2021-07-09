package br.com.zupacademy.desafiomercadolivre.produto;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    @Deprecated
    public CaracteristicaRequest(){
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica toModel(){
        return new Caracteristica(this.nome, this.descricao);
    }
}
