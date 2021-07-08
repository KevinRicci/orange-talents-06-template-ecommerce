package br.com.zupacademy.desafiomercadolivre.categoria;

import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<CategoriaResponse> cadastra(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.toModel(categoriaRepository);
        categoriaRepository.save(categoria);

        URI uri = UriComponentsBuilder.newInstance().path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new CategoriaResponse(categoria));
    }
}
