package br.com.zupacademy.desafiomercadolivre.categoria;

import br.com.zupacademy.desafiomercadolivre.validacao.ExistsId;
import br.com.zupacademy.desafiomercadolivre.validacao.UniqueValue;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

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
        validaIdCategoriaMae(categoria, categoriaRepository);
        return categoria;
    }

    private void validaIdCategoriaMae(Categoria categoria, CategoriaRepository categoriaRepository){
        if(idCategoriaMae != null){
            Optional<Categoria> categoriaMae = categoriaRepository.findById(idCategoriaMae);
            if(categoriaMae.isPresent()){
                categoria.setMae(categoriaMae.get());
            }
            else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "campo idCategoriaMae não existe");
        }
    }

}
