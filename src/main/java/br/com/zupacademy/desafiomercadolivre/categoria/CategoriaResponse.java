package br.com.zupacademy.desafiomercadolivre.categoria;

public class CategoriaResponse {

    private Long id;
    private String nome;
    private Categoria categoriaMae;

    public CategoriaResponse(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.categoriaMae = categoria.getCategoria();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoriaMae() {
        return categoriaMae;
    }
}
