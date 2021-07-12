package br.com.zupacademy.desafiomercadolivre.enviador;

import br.com.zupacademy.desafiomercadolivre.compra.Compra;
import br.com.zupacademy.desafiomercadolivre.usuario.Usuario;
import org.springframework.stereotype.Component;

@Component
public class EnviaEmailNovaCompra {

    public void envia(Usuario dono, Compra compra){
        System.out.println("e-mail enviado para "+dono.getLogin()+"sobre o produto "+compra.getProduto().getNome());
    }
}
