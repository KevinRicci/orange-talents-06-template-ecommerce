package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioResponse;

import java.util.List;
import java.util.stream.Collectors;

public class OpiniaoResponse {

    private Long id;
    private int nota;
    private String titulo;
    private String descricao;
    private UsuarioResponse usuarioResponse;

    public OpiniaoResponse(Opiniao opiniao) {
        this.id = opiniao.getId();
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.usuarioResponse = new UsuarioResponse(opiniao.getUsuario());
    }

    public static List<OpiniaoResponse> converter(List<Opiniao> opinioes){
        return opinioes.stream().map(op -> new OpiniaoResponse(op)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
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

    public UsuarioResponse getUsuarioResponse() {
        return usuarioResponse;
    }
}
