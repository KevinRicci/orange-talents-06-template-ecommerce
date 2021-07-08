package br.com.zupacademy.desafiomercadolivre.categoria;

import br.com.zupacademy.desafiomercadolivre.validacao.UniqueValue;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank @UniqueValue(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    private Long idCategoriaMae;

    @Deprecated
    public CategoriaRequest() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCategoriaMae(Long idCategoriaMae) {
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository){
        Categoria categoria = new Categoria(this.nome);
        if(idCategoriaMae != null){
            Optional<Categoria> categoriaMae = categoriaRepository.findById(idCategoriaMae);
            categoria.setMae(categoriaMae.get());
        }
        return categoria;
    }

}
