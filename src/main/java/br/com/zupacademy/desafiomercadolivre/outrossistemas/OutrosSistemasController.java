package br.com.zupacademy.desafiomercadolivre.outrossistemas;

import br.com.zupacademy.desafiomercadolivre.compra.NovaCompraNFRequest;
import br.com.zupacademy.desafiomercadolivre.compra.RankingNovaCompraRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemasController {

    @PostMapping(value = "/notas-fiscais")
    public void criaNota(@Valid @RequestBody NovaCompraNFRequest request) throws InterruptedException {
        System.out.println("criando nota "+request);
        Thread.sleep(150);
    }

    @PostMapping(value = "/ranking")
    public void ranking(@Valid @RequestBody RankingNovaCompraRequest request) throws InterruptedException {
        System.out.println("criando ranking"+request);
        Thread.sleep(150);
    }

}
