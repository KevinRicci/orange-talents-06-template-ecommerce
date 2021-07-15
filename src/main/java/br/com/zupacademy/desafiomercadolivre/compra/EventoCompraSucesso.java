package br.com.zupacademy.desafiomercadolivre.compra;

/**
 * Todo evento pós compra deve implementar essa interface
 */
public interface EventoCompraSucesso {

    void processa(Compra compra);

}
