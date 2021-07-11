package br.com.zupacademy.desafiomercadolivre.produto.opiniao;

import br.com.zupacademy.desafiomercadolivre.produto.Produto;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OpiniaoRequest {

    @Min(1) @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String descricao;

    public OpiniaoRequest(int nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    @Deprecated
    public OpiniaoRequest(){}

    public Opiniao toModel(Produto produto, Usuario usuario){
        return new Opiniao(this.nota, this.titulo, this.descricao, produto, usuario);
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }
}
