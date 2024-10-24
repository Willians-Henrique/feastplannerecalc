package feastplannerecalc.model;

import jakarta.persistence.*;

/**
 * Classe que representa as categorias de salgados utilizados na simulação,
 * como fritos e assados.
 * 
 * A tabela mapeada é "comidas_categoria_salgado".
 */
@Entity
@Table(name = "comidas_categoria_salgado")
public class ComidaCategoriaSalgado {

    /**
     * Identificador único da categoria de salgado.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da categoria do salgado (ex: Frito, Assado).
     * Este campo é obrigatório.
     */
    @Column(name = "categoria", nullable = false)
    private String categoria;

    // Getters e Setters

    /**
     * Obtém o identificador único da categoria de salgado.
     * 
     * @return id da categoria.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da categoria de salgado.
     * 
     * @param id Novo identificador da categoria.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da categoria do salgado (ex: Frito, Assado).
     * 
     * @return categoria do salgado.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define o nome da categoria do salgado.
     * 
     * @param categoria Nova categoria do salgado.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
