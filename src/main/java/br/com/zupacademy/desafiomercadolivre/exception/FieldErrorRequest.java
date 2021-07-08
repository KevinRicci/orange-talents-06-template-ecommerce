package br.com.zupacademy.desafiomercadolivre.exception;

public class FieldErrorRequest {

    private String campo;
    private String mensagem;

    public FieldErrorRequest(String campo, String mensagem){
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
