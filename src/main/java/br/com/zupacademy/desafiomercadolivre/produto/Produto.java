package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.categoria.Categoria;
import br.com.zupacademy.desafiomercadolivre.produto.validacao.TamanhoMinimo;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import br.com.zupacademy.desafiomercadolivre.validacao.ExistsId;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id @GeneratedValue
    private Long id;
    @NotBlank @Column(nullable = false)
    private String nome;
    @NotNull @Min(1) @Column(nullable = false)
    private BigDecimal valor;
    @NotNull @Min(0) @Column(nullable = false)
    private int quantidade;
    @NotNull @TamanhoMinimo @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Caracteristica> caracteristicas;
    @NotBlank @Size(max = 1000)
    private String descricao;
    @NotNull @ManyToOne
    private Categoria categoria;
    private LocalDateTime instante = LocalDateTime.now();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Imagem> imagens = new ArrayList<>();
    @ManyToOne
    private Usuario usuario;

    public Produto(String nome, BigDecimal valor, int quantidade, List<Caracteristica> caracteristicas, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;

        this.caracteristicas.forEach(e -> e.setProduto(this));
    }

    @Deprecated
    public Produto(){}

    public void associaImagens(List<String> links) {
        List<Imagem> imagens = links.stream()
                .map(link -> new Imagem(this, link))
                .collect(Collectors.toList());

        this.imagens.addAll(imagens);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public List<Caracteristica> getCaracteristicas() {
        return Collections.unmodifiableList(caracteristicas);
    }

    public List<Imagem> getImagens(){
        return Collections.unmodifiableList(imagens);
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public boolean pertenceAo(Usuario usuario){
        return this.getUsuario().equals(usuario);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicas +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", instante=" + instante +
                ", imagens=" + imagens +
                ", usuario=" + usuario +
                '}';
    }
}
