package br.com.zupacademy.desafiomercadolivre.compra;

import br.com.zupacademy.desafiomercadolivre.produto.Produto;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;
    @NotNull @ManyToOne @Valid
    private Produto produto;
    @NotNull
    private BigDecimal valor;
    @Min(1)
    private int quantidade;
    @NotNull @ManyToOne @Valid
    private Usuario comprador;
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();


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

    public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.toTransacao(this);

        Assert.isTrue(!this.transacoes.contains(novaTransacao),
                "Já existe uma transacao igual a essa processada "
                        + novaTransacao);
        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(),"Esse compra já foi concluída com sucesso");

        this.transacoes.add(novaTransacao);
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());

        Assert.isTrue(transacoesConcluidasComSucesso.size() <= 1,"Tem mais de uma transação concluída na compra "+this.id);

        return transacoesConcluidasComSucesso;
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
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

    public Usuario getDonoProduto() {
        return this.produto.getUsuario();
    }
}
