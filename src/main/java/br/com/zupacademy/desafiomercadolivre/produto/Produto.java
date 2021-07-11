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
import java.util.Objects;
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
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Imagem> imagens = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Opiniao> opinioes = new ArrayList<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Pergunta> perguntas = new ArrayList<>();
    @NotBlank @Size(max = 1000)
    private String descricao;
    @NotNull @ManyToOne
    private Categoria categoria;
    private LocalDateTime instante = LocalDateTime.now();
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

    public void associaOpiniao(Opiniao opiniao){
        if(opiniao.getProduto() != null && opiniao.getProduto().equals(this) && opiniao.getUsuario() != null) {
            this.opinioes.add(opiniao);
        }else throw new IllegalArgumentException("Opinião precisa estar associada a um produto e usuário");
    }

    public void associaPergunta(Pergunta pergunta){
        if(pergunta.getProduto() != null && pergunta.getProduto().equals(this) && pergunta.getUsuario() != null){
            this.perguntas.add(pergunta);
        }
    }

    public boolean pertenceAo(Usuario usuario){
        return this.getUsuario().equals(usuario);
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

    public List<Opiniao> getOpinioes(){ return Collections.unmodifiableList(opinioes); }

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

    public List<Pergunta> getPerguntas() {
        return perguntas;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return quantidade == produto.quantidade && id.equals(produto.id) && nome.equals(produto.nome) && valor.equals(produto.valor) && caracteristicas.equals(produto.caracteristicas) && descricao.equals(produto.descricao) && categoria.equals(produto.categoria) && instante.equals(produto.instante) && usuario.equals(produto.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, valor, quantidade, caracteristicas, descricao, categoria, instante, usuario);
    }
}
