package br.com.zupacademy.desafiomercadolivre.produto.caracteristica;

import java.util.ArrayList;
import java.util.List;

public class CaracteristicaResponse {

    private Long id;
    private String nome;
    private String descricao;

    public CaracteristicaResponse(Caracteristica caracteristica){
        this.id = caracteristica.getId();
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public static List<CaracteristicaResponse> converter(List<Caracteristica> caracteristicas){
        List<CaracteristicaResponse> responses = new ArrayList<>();
        caracteristicas.forEach(c ->{
            responses.add(new CaracteristicaResponse(c));
        });
        return responses;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
