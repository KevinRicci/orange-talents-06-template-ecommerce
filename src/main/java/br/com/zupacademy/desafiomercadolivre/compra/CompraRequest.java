package br.com.zupacademy.desafiomercadolivre.compra;

import br.com.zupacademy.desafiomercadolivre.produto.Produto;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import br.com.zupacademy.desafiomercadolivre.validacao.ExistsId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CompraRequest {

    @NotNull @ExistsId(domainClass = Produto.class)
    private Long idProduto;
    @NotNull
    private GatewayPagamento gatewayPagamento;
    @Min(1)
    private int quantidade;

    public CompraRequest(Long idProduto, GatewayPagamento gatewayPagamento, int quantidade) {
        this.idProduto = idProduto;
        this.gatewayPagamento = gatewayPagamento;
        this.quantidade = quantidade;
    }

    @Deprecated
    public CompraRequest() {
    }

    public Compra toModel(Produto produto, Usuario comprador){
        return new Compra(this.gatewayPagamento, produto, produto.getValor(), quantidade, comprador);
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public GatewayPagamento getGatewayPagamento() {
        return gatewayPagamento;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
