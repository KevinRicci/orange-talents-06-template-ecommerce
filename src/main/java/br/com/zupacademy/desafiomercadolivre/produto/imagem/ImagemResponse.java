package br.com.zupacademy.desafiomercadolivre.produto.imagem;

import java.util.List;
import java.util.stream.Collectors;

public class ImagemResponse {

    private Long id;
    private String link;

    public ImagemResponse(Imagem imagem){
        this.id = imagem.getId();
        this.link = imagem.getLink();
    }

    public static List<ImagemResponse> converter(List<Imagem> imagens){
        return imagens.stream().map(i -> new ImagemResponse(i)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }
}
