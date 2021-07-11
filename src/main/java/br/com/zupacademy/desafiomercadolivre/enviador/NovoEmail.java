package br.com.zupacademy.desafiomercadolivre.enviador;

import br.com.zupacademy.desafiomercadolivre.produto.pergunta.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovoEmail {

    @Autowired
    private EnviaEmailNovaPergunta enviaEmailNovaPergunta;

    public void novaPergunta(Pergunta pergunta){
        this.enviaEmailNovaPergunta.envia(pergunta);
    }
}
