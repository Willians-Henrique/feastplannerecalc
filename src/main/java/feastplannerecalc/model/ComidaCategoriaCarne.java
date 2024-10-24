package feastplannerecalc.model;

import jakarta.persistence.*;

/**
 * Classe que representa as categorias de carnes utilizadas na simulação, 
 * como bovino, suíno e frango, diferenciando se possuem ou não osso.
 * 
 * A tabela mapeada é "comidas_categoria_carne".
 */
@Entity
@Table(name = "comidas_categoria_carne")
public class ComidaCategoriaCarne {

    /**
     * Identificador único da categoria de carne.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome da categoria da carne (ex: Bovino com Osso, Suíno sem Osso).
     * Este campo é obrigatório.
     */
    @Column(name = "categoria", nullable = false)
    private String categoria;

    // Getters e Setters

    /**
     * Obtém o identificador único da categoria de carne.
     * 
     * @return id da categoria.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da categoria de carne.
     * 
     * @param id Novo identificador da categoria.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da categoria da carne (ex: Bovino com Osso, Suíno sem Osso).
     * 
     * @return categoria da carne.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define o nome da categoria da carne.
     * 
     * @param categoria Nova categoria da carne.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
