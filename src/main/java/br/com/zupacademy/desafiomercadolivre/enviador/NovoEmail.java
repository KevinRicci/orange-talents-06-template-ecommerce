package br.com.zupacademy.desafiomercadolivre.enviador;

import br.com.zupacademy.desafiomercadolivre.compra.Compra;
import br.com.zupacademy.desafiomercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NovoEmail {

    @Autowired
    private EnviaEmailNovaPergunta enviaEmailNovaPergunta;
    @Autowired
    private EnviaEmailNovaCompra enviaEmailNovaCompra;

    public void novaPergunta(Pergunta pergunta){
        this.enviaEmailNovaPergunta.envia(pergunta);
    }

    public void novaCompra(Usuario dono, Compra compra){
        enviaEmailNovaCompra.envia(dono, compra);
    }
}
