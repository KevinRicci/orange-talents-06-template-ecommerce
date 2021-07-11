package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PerguntaResponse {

    private Long id;
    private String titulo;
    private UsuarioResponse usuario;

    public PerguntaResponse(Pergunta pergunta) {
        this.id = pergunta.getId();
        this.titulo = pergunta.getTitulo();
        this.usuario = new UsuarioResponse(pergunta.getUsuario());
    }

    public static List<PerguntaResponse> converter(List<Pergunta> perguntas){
        return perguntas.stream().map(p -> new PerguntaResponse(p)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public UsuarioResponse getUsuario() {
        return usuario;
    }
}
