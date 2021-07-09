package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.categoria.Categoria;
import br.com.zupacademy.desafiomercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.desafiomercadolivre.produto.validacao.TamanhoMinimo;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioRepository;
import br.com.zupacademy.desafiomercadolivre.validacao.ExistsId;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull @Min(1)
    private BigDecimal valor;
    @NotNull @Min(0)
    private int quantidade;
    @NotNull @TamanhoMinimo
    private List<@Valid CaracteristicaRequest> caracteristicasRequests;
    @NotBlank @Size(max = 1000)
    private String descricao;
    @NotNull @ExistsId(domainClass = Categoria.class)
    private Long idCategoria;

    @Deprecated
    public ProdutoRequest() {
    }

    public ProdutoRequest(String nome, BigDecimal valor, int quantidade, List<CaracteristicaRequest> caracteristicasRequests, String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicasRequests = caracteristicasRequests;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario){
        if(this.caracteristicasRequests != null && idCategoria != null && usuario != null){
            Categoria categoria = categoriaRepository.getById(this.idCategoria);
            List<Caracteristica> caracteristicas = new ArrayList<>();
            this.caracteristicasRequests.forEach(cr -> caracteristicas.add(cr.toModel()));

            return new Produto(this.nome, this.valor, this.quantidade, caracteristicas, this.descricao, categoria, usuario);
        }else throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", caracteristicas=" + caracteristicasRequests +
                ", descricao='" + descricao + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
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

    public List<CaracteristicaRequest> getCaracteristicasRequests() {
        return caracteristicasRequests;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }
}
