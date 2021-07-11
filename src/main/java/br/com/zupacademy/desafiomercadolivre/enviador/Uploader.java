package br.com.zupacademy.desafiomercadolivre.enviador;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Uploader {

    /**
     *
     * @param imagens
     * @return links para imagens que foram uploadadas
     */
    List<String> envia(List<MultipartFile> imagens);

}
