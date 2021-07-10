package br.com.zupacademy.desafiomercadolivre.produto;

import br.com.zupacademy.desafiomercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private Uploader uploaderFake;

    @PostMapping
    @Transactional
    public ResponseEntity<ProdutoResponse> cadastra(@RequestBody @Valid ProdutoRequest produtoRequest){
        Usuario usuario = usuarioRepository.findByLogin("kevinricci@gmail.com").get(); //mockando usuário
        Produto produto = produtoRequest.toModel(this.categoriaRepository, usuario);
        produtoRepository.save(produto);

        URI uri = UriComponentsBuilder.newInstance().path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
    }

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<ProdutoResponse> adicionaImagens(@PathVariable("id") Long id, @Valid ImagemRequest request) {
        Usuario usuario = usuarioRepository.findByLogin("kevinricci@gmail.com").get(); //mockando usuário
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()) {
            if(!produto.get().pertenceAo(usuario)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            List<String> links = uploaderFake.envia(request.getImagens());
            produto.get().associaImagens(links);
            produtoRepository.saveAndFlush(produto.get());

            return ResponseEntity.ok(new ProdutoResponse(produto.get()));
        }else return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/opinioes")
    @Transactional
    public ResponseEntity<ProdutoResponse> adicionaOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest opiniaoRequest){
        Usuario usuario = usuarioRepository.findByLogin("kevinricci@gmail.com").get(); //mockando usuário
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            Opiniao opiniao = opiniaoRequest.toModel(produto.get(), usuario);
            produto.get().associaOpiniao(opiniao);
            produtoRepository.saveAndFlush(produto.get());

            return ResponseEntity.ok(new ProdutoResponse(produto.get()));
        }else return ResponseEntity.notFound().build();
    }
}
