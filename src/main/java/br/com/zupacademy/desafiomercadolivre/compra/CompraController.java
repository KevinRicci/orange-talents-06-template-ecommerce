package br.com.zupacademy.desafiomercadolivre.compra;

import br.com.zupacademy.desafiomercadolivre.enviador.NovoEmail;
import br.com.zupacademy.desafiomercadolivre.produto.Produto;
import br.com.zupacademy.desafiomercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import br.com.zupacademy.desafiomercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private NovoEmail novoEmail;

    @PostMapping
    @Transactional
    public ResponseEntity<String> comprar(@RequestBody @Valid CompraRequest compraRequest, UriComponentsBuilder uri) throws BindException {
        Usuario comprador = usuarioRepository.findByLogin("kevinricci@gmail.com").get(); //mockando usuário
        Produto produtoCompra = produtoRepository.findById(compraRequest.getIdProduto()).get();

        boolean estoqueAbatido = produtoCompra.abateEstoque(compraRequest.getQuantidade());
        if(estoqueAbatido){
            Compra compra = compraRequest.toModel(produtoCompra, comprador);
            compraRepository.save(compra);
            novoEmail.novaCompra(produtoCompra.getUsuario(), compra);
            if(compraRequest.getGatewayPagamento().toString().equals("PAGSEGURO")){
                String urlRetornoPagSeguro = uri.path("/retorno-pagseguro/{id}").buildAndExpand(compra.getId()).toString();

                return ResponseEntity.ok("pagseguro.com/"+compra.getId()+"?redirectUrl="+urlRetornoPagSeguro);
            }
            if(compraRequest.getGatewayPagamento().toString().equals("PAYPAL")){
                String urlRetornoPaypal = uri.path("/retorno-paypal/{id}").buildAndExpand(compra.getId()).toString();

                return ResponseEntity.ok("paypal.com/"+compra.getId()+"?redirectUrl="+urlRetornoPaypal);
            }
        }else{
            BindException problemaComEstoque = new BindException(compraRequest, "quantidade");
            problemaComEstoque.rejectValue("quantidade",null, "A quantidade é maior que o estoque");
            throw problemaComEstoque;
        }
        return null;
    }
}
