package feastplannerecalc.model;

import jakarta.persistence.*;

/**
 * Classe que define as pessoas, categorizando-as em dois grupos: 
 * pessoas que comem carne e pessoas que não comem carne.
 * 
 * A tabela mapeada é "pessoas".
 */
@Entity
@Table(name = "pessoas")
public class Pessoa {

    /**
     * Identificador único da pessoa.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo de pessoa, indicando se ela come ou não carne.
     * Este campo é obrigatório.
     */
    @Column(name = "tipo", nullable = false)
    private String tipo;

    // Getters e Setters

    /**
     * Obtém o identificador único da pessoa.
     * 
     * @return id da pessoa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da pessoa.
     * 
     * @param id Novo identificador da pessoa.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o tipo de pessoa, indicando se ela come carne ou não.
     * 
     * @return tipo da pessoa.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo de pessoa, indicando se ela come carne ou não.
     * 
     * @param tipo Novo tipo da pessoa (ex: "Come carne" ou "Não come carne").
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
