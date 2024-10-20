package feastplannerecalc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * The TestEntity class is responsible for holding data regarding test entries.
 */
@Entity
@Table(name = "test_entities") // Nome da tabela no banco de dados
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false) // Nome não pode ser nulo
    private String name;

    // Construtor padrão
    public TestEntity() {
        // Construtor vazio, se necessário
    }

    // Getters e Setters

    /**
     * Get the ID of the entity.
     *
     * @return the ID of the entity.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the entity.
     *
     * @param id the ID to be set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of the entity.
     *
     * @return the name of the entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the entity.
     *
     * @param name the name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }
}
