package br.com.zupacademy.desafiomercadolivre.produto.imagem;

import br.com.zupacademy.desafiomercadolivre.enviador.Uploader;
import br.com.zupacademy.desafiomercadolivre.produto.Produto;
import br.com.zupacademy.desafiomercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.desafiomercadolivre.produto.ProdutoResponse;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ImagemController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ImagemRepository imagemRepository;
    @Autowired
    private Uploader uploaderFake;

    @PostMapping(value = "/{id}/imagens")
    @Transactional
    public ResponseEntity<List<ImagemResponse>> adicionaImagens(@PathVariable("id") Long id, @Valid ImagemRequest request) {
        Usuario usuario = usuarioRepository.findByLogin("kevinricci@gmail.com").get(); //mockando usuário
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isPresent()) {
            if(!produto.get().pertenceAo(usuario)) throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            List<String> links = uploaderFake.envia(request.getImagens());
            List<Imagem> imagens = links.stream().map(link -> new Imagem(produto.get(), link)).collect(Collectors.toList());
            imagemRepository.saveAll(imagens);

            URI uri = UriComponentsBuilder.newInstance().path("/produtos/{id}/imagens").buildAndExpand(produto.get().getId()).toUri();
            return ResponseEntity.created(uri).body(ImagemResponse.converter(imagens));
        }else return ResponseEntity.notFound().build();
    }
}
