package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Pergunta {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Usuario usuario;

    public Pergunta(String titulo, Produto produto, Usuario usuario) {
        this.titulo = titulo;
        this.produto = produto;
        this.usuario = usuario;
    }

    @Deprecated
    public Pergunta(){}

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
