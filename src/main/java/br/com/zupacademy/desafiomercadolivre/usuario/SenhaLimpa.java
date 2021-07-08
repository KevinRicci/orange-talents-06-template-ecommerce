package br.com.zupacademy.desafiomercadolivre.usuario;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;

public class SenhaLimpa {

    @NotBlank
    private String senha;

    public SenhaLimpa(@NotBlank String senhaLimpa){
        this.senha = senhaLimpa;
    }

    public String encoda(){
        return new BCryptPasswordEncoder().encode(this.senha);
    }
}
