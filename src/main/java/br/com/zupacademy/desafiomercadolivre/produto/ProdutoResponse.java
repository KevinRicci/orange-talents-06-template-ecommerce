package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.categoria.Categoria;
import br.com.zupacademy.desafiomercadolivre.categoria.CategoriaResponse;
import br.com.zupacademy.desafiomercadolivre.produto.validacao.TamanhoMinimo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private int quantidade;
    private List<CaracteristicaResponse> caracteristicasResponse;
    private String descricao;
    private CategoriaResponse categoriaResponse;
    private LocalDateTime instante;
    private List<ImagemResponse> imagensResponse;

    public ProdutoResponse(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();
        this.caracteristicasResponse = CaracteristicaResponse.converter(produto.getCaracteristicas());
        this.descricao = produto.getDescricao();
        this.categoriaResponse = new CategoriaResponse(produto.getCategoria());
        this.instante = produto.getInstante();
        this.imagensResponse = ImagemResponse.converter(produto.getImagens());
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

    public List<CaracteristicaResponse> getCaracteristicasResponse() {
        return caracteristicasResponse;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaResponse getCategoriaResponse() {
        return categoriaResponse;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public List<ImagemResponse> getImagensResponse() {
        return imagensResponse;
    }
}
