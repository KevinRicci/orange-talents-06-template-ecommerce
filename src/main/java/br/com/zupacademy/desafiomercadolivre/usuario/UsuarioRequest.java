package br.com.zupacademy.desafiomercadolivre.usuario;

import br.com.zupacademy.desafiomercadolivre.validacao.UniqueValue;
import com.sun.istack.NotNull;
import org.springframework.context.annotation.Profile;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioRequest {

    @NotBlank @Email @UniqueValue(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @Size(min = 6) @NotBlank
    private String senha;

    public UsuarioRequest(@NotBlank @Email String login, @Size(min = 6) @NotBlank String senha){
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Usuario toModel(){
        return new Usuario(this.login, new SenhaLimpa(this.senha));
    }
}