package br.com.zupacademy.desafiomercadolivre.compra;

public enum StatusRetornoPagSeguro {

    SUCESSO,ERRO;

    public StatusTransacao normaliza() {
        if(this.equals(SUCESSO)) {
            return StatusTransacao.sucesso;
        }else return StatusTransacao.erro;
    }
}
