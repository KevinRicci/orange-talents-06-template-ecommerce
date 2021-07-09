package br.com.zupacademy.desafiomercadolivre.autenticacao;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AutenticacaoRequest {

    private String email;
    private String senha;

    public AutenticacaoRequest(String email, String senha){
        this.email = email;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
