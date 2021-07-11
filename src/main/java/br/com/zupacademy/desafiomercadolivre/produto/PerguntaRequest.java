package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    public PerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    @Deprecated
    public PerguntaRequest(){}

    public Pergunta toModel(Produto produto, Usuario usuario){
        return new Pergunta(this.titulo, produto, usuario);
    }

    public String getTitulo() {
        return titulo;
    }
}
