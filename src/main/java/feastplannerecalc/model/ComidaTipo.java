package feastplannerecalc.model;

import jakarta.persistence.*;

/**
 * Classe que define os tipos de comida que podem ser usados nas simulações,
 * como churrasco, salgado ou itens obrigatórios (ex: copos, pratos, guardanapos).
 * 
 * A tabela mapeada é "comidas_tipo".
 */
@Entity
@Table(name = "comidas_tipo")
public class ComidaTipo {

    /**
     * Identificador único do tipo de comida.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do tipo de comida (ex: churrasco, salgado, obrigatório).
     * Este campo é obrigatório.
     */
    @Column(name = "tipo", nullable = false)
    private String tipo;

    // Getters e Setters

    /**
     * Obtém o identificador único do tipo de comida.
     * 
     * @return id do tipo de comida.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único do tipo de comida.
     * 
     * @param id Novo identificador do tipo de comida.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome do tipo de comida (ex: churrasco, salgado, obrigatório).
     * 
     * @return tipo de comida.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o nome do tipo de comida (ex: churrasco, salgado, obrigatório).
     * 
     * @param tipo Novo nome do tipo de comida.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
