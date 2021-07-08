package br.com.zupacademy.desafiomercadolivre.usuario;

public class UsuarioResponse {

    private Long id;
    private String login;

    public UsuarioResponse(Usuario usuario){
        this.id = usuario.getId();
        this.login = usuario.getLogin();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }
}
