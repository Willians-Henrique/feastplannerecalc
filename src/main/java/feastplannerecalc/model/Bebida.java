package feastplannerecalc.model;


import jakarta.persistence.*;

/**
 * Classe que representa uma bebida no sistema.
 * Esta classe armazena as bebidas e inclui uma chave estrangeira para o tipo de bebida,
 * que indica se é uma bebida alcoólica ou não alcoólica.
 * 
 * A tabela mapeada é "bebidas".
 */

@Entity
@Table(name = "bebidas")
public class Bebida {

    /**
     * Identificador único da bebida.
     * Gerado automaticamente pelo banco de dados.
     */
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nome da bebida.
     * Este campo é obrigatório.
     */
    
    @Column(name = "bebida", nullable = false)
    private String bebida;
    
    /**
     * Tipo da bebida, que está relacionado à tabela "bebida_tipo".
     * Este campo é obrigatório e define se a bebida é alcoólica ou não alcoólica.
     */
    
    @ManyToOne
    @JoinColumn(name = "tipo", nullable = false)
    private BebidaTipo tipo;

    // Getters e Setters

    /**
     * Obtém o identificador da bebida.
     * 
     * @return id da bebida.
     */
    
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador da bebida.
     * 
     * @param id Novo identificador da bebida.
     */
    
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o nome da bebida.
     * 
     * @return nome da bebida.
     */
    
    public String getBebida() {
        return bebida;
    }

    /**
     * Define o nome da bebida.
     * 
     * @param bebida Novo nome da bebida.
     */

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }
    /**
     * Obtém o tipo da bebida.
     * 
     * @return tipo da bebida.
     */
    
    public BebidaTipo getTipo() {
        return tipo;
    }
    
    /**
     * Define o tipo da bebida.
     * 
     * @param tipo Novo tipo da bebida.
     */
    
    public void setTipo(BebidaTipo tipo) {
        this.tipo = tipo;
    }
}
