package br.com.zupacademy.desafiomercadolivre.usuario;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime instanteCadastro  = LocalDateTime.now();
    @NotBlank @Email @Column(nullable = false, unique = true)
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id.equals(usuario.id) && instanteCadastro.equals(usuario.instanteCadastro) && login.equals(usuario.login) && senha.equals(usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, instanteCadastro, login, senha);
    }
}
