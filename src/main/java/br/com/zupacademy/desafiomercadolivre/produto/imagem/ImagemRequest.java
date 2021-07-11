package br.com.zupacademy.desafiomercadolivre.produto.imagem;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ImagemRequest {

    @NotNull
    private List<MultipartFile> imagens = new ArrayList<>();

    @Deprecated
    public ImagemRequest(){}

    //necessário pelo spring
    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    @Override
    public String toString() {
        return "ImagemRequest{" +
                "imagens=" + imagens +
                '}';
    }
}
