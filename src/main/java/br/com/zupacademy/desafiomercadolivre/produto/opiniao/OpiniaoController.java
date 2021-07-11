package br.com.zupacademy.desafiomercadolivre.produto.opiniao;

import br.com.zupacademy.desafiomercadolivre.produto.Produto;
import br.com.zupacademy.desafiomercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.desafiomercadolivre.produto.ProdutoResponse;
import br.com.zupacademy.desafiomercadolivre.produto.pergunta.PerguntaResponse;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @PostMapping("/{id}/opinioes")
    @Transactional
    public ResponseEntity<OpiniaoResponse> adicionaOpiniao(@PathVariable Long id, @RequestBody @Valid OpiniaoRequest opiniaoRequest){
        Usuario usuario = usuarioRepository.findByLogin("kevinricci@gmail.com").get(); //mockando usuário
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            Opiniao opiniao = opiniaoRequest.toModel(produto.get(), usuario);
            opiniaoRepository.save(opiniao);

            URI uri = UriComponentsBuilder.newInstance().path("/produtos/{id}/opinioes/{id}").buildAndExpand(produto.get().getId(), opiniao.getId()).toUri();
            return ResponseEntity.created(uri).body(new OpiniaoResponse(opiniao));
        }else return ResponseEntity.notFound().build();
    }
}
