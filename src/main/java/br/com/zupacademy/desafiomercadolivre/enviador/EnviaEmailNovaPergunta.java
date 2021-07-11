package br.com.zupacademy.desafiomercadolivre.enviador;

import br.com.zupacademy.desafiomercadolivre.produto.Pergunta;
import org.springframework.stereotype.Service;

@Service
public class EnviaEmailNovaPergunta {

    public void envia(Pergunta pergunta){
        System.out.println("e-mail enviado");
    }
}
