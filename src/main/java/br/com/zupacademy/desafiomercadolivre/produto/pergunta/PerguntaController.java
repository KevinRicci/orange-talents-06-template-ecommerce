package br.com.zupacademy.desafiomercadolivre.produto.pergunta;

import br.com.zupacademy.desafiomercadolivre.enviador.NovoEmail;
import br.com.zupacademy.desafiomercadolivre.produto.Produto;
import br.com.zupacademy.desafiomercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.desafiomercadolivre.produto.ProdutoResponse;
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
public class PerguntaController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private PerguntaRepository perguntaRepository;
    @Autowired
    private NovoEmail novoEmail;

    @PostMapping("/{id}/perguntas")
    @Transactional
    public ResponseEntity<PerguntaResponse> adicionaPergunta(@PathVariable Long id, @RequestBody @Valid PerguntaRequest perguntaRequest){
        Usuario usuario = usuarioRepository.findByLogin("kevinricci@gmail.com").get(); //mockando usuário
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()){
            Pergunta pergunta = perguntaRequest.toModel(produto.get(), usuario);
            perguntaRepository.save(pergunta);

            novoEmail.novaPergunta(pergunta);

            URI uri = UriComponentsBuilder.newInstance().path("/produtos/{id}/perguntas/{id}").buildAndExpand(produto.get().getId(), pergunta.getId()).toUri();
            return ResponseEntity.created(uri).body(new PerguntaResponse(pergunta));
        }else return ResponseEntity.notFound().build();
    }
}
