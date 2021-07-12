package br.com.zupacademy.desafiomercadolivre.enviador;

import br.com.zupacademy.desafiomercadolivre.produto.pergunta.Pergunta;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailNovaPergunta {

    public void envia(Pergunta pergunta){
        System.out.println("e-mail enviado");
    }
}
