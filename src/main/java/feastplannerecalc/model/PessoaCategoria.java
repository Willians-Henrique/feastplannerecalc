package feastplannerecalc.model;

import jakarta.persistence.*;

/**
 * Classe que define as categorias de pessoas que comem carne. 
 * Existem 4 tipos de categorias: Homem, Mulher, Criança e Comilão.
 * 
 * A tabela mapeada é "pessoas_categoria". Essa classe é usada para 
 * associar o tipo de pessoa com a quantidade padrão de comida que cada categoria consome.
 */
@Entity
@Table(name = "pessoas_categoria")
public class PessoaCategoria {

    /**
     * Identificador único da categoria de pessoa.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da categoria da pessoa. Ex: "Homem", "Mulher", "Criança" ou "Comilão".
     * Este campo é obrigatório.
     */
    @Column(name = "categoria", nullable = false)
    private String categoria;

    // Getters e Setters

    /**
     * Obtém o identificador único da categoria de pessoa.
     * 
     * @return id da categoria.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da categoria de pessoa.
     * 
     * @param id Novo identificador da categoria.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da categoria da pessoa, como "Homem", "Mulher", "Criança" ou "Comilão".
     * 
     * @return categoria da pessoa.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define o nome da categoria da pessoa.
     * 
     * @param categoria Novo nome da categoria da pessoa.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
