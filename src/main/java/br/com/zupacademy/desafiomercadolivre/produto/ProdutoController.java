package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioRepository;
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
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoResponse> cadastra(@RequestBody @Valid ProdutoRequest produtoRequest){
        Usuario usuario = usuarioRepository.findByLogin("kevinricci@gmail.com").get();
        Produto produto = produtoRequest.toModel(this.categoriaRepository, usuario);
        produtoRepository.save(produto);

        URI uri = UriComponentsBuilder.newInstance().path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
    }
}
