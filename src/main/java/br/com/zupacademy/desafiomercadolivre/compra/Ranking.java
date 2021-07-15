package br.com.zupacademy.desafiomercadolivre.compra;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso{

    @Override
    public void processa(Compra compra) {
        Assert.isTrue(compra.processadaComSucesso(),"Compra não processada com sucesso"+compra);

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(),
                "idDonoProduto", compra.getDonoProduto().getId());

        restTemplate.postForEntity("http://localhost:8080/ranking",
                request, String.class);
    }

}