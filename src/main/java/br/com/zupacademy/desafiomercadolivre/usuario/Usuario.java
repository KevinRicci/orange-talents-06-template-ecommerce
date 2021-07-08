package br.com.zupacademy.desafiomercadolivre.usuario;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime instanteCadastro  = LocalDateTime.now();
    @NotBlank @Email @Column(nullable = false)
    private String login;
    @Size(min = 6) @NotBlank @Column(nullable = false)
    private String senha;

    public Usuario(@NotBlank @Email String login, @NotNull SenhaLimpa senhaLimpa){
        Assert.isTrue(StringUtils.hasLength(login), "O login não pode ser em branco");
        Assert.notNull(senhaLimpa, "A senha não pode ser em branco");
        this.login = login;
        this.senha = senhaLimpa.encoda();
        Assert.hasLength(this.senha, "Falha ao setar senha");
    }

    @Deprecated
    public Usuario(){}

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
