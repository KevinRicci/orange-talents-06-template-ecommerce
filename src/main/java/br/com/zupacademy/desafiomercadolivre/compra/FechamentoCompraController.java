package br.com.zupacademy.desafiomercadolivre.compra;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;


@RestController
public class FechamentoCompraController {

    @Autowired
    private CompraRepository compraRepository;
    @Autowired
    private EventosNovaCompra eventosNovaCompra;

    @PostMapping(value = "/retorno-pagseguro/{id}")
    @Transactional
    public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @RequestBody @Valid RetornoPagSeguroRequest request) {
        return processa(idCompra, request);
    }

    @PostMapping(value = "/retorno-paypal/{id}")
    @Transactional
    public String processamentoPaypal(@PathVariable("id") Long idCompra, @RequestBody @Valid RetornoPayPalRequest request) {
        return processa(idCompra, request);
    }

    private String processa(Long idCompra, RetornoGatewayPagamento retornoGatewayPagamento) {
        Compra compra = compraRepository.findById(idCompra).get();
        compra.adicionaTransacao(retornoGatewayPagamento);
        compraRepository.saveAndFlush(compra);
        eventosNovaCompra.processa(compra);

        return compra.toString();
    }
}
