package br.com.zupacademy.desafiomercadolivre.compra;

import br.com.zupacademy.desafiomercadolivre.produto.Produto;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Enumerated
    private GatewayPagamento gatewayPagamento;
    @NotNull @ManyToOne @Valid
    private Produto produto;
    @NotNull
    private BigDecimal valor;
    @Min(1)
    private int quantidade;
    @NotNull @ManyToOne @Valid
    private Usuario comprador;

    public Compra(GatewayPagamento gatewayPagamento, Produto produto, BigDecimal valor, int quantidade, Usuario comprador) {
        this.gatewayPagamento = gatewayPagamento;
        this.produto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
        this.comprador = comprador;
    }

    @Deprecated
    public Compra(){
    }

    public Long getId() {
        return id;
    }

    public GatewayPagamento getGatewayPagemanto() {
        return gatewayPagamento;
    }

    public Produto getProduto() {
        return produto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }
}
