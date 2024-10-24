package feastplannerecalc.model;

import jakarta.persistence.*;

	/**
	 * Classe que representa os tipos de bebidas, como alcoólicas ou não alcoólicas.
	 * A chave estrangeira desta classe é usada na tabela "bebidas" para definir o tipo da bebida.
	 * 
	 * A tabela mapeada é "bebidas_tipos".
	 */

@Entity
@Table(name = "bebidas_tipos")
public class BebidaTipo {

    /**
     * Identificador único do tipo de bebida.
     * Gerado automaticamente pelo banco de dados.
     */
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Descrição do tipo de bebida (ex: "Alcoólica", "Não Alcoólica").
     * Este campo é obrigatório.
     */
    
    @Column(name = "tipo", nullable = false)
    private String tipo;

    // Getters e Setters

    /**
     * Obtém o identificador do tipo de bebida.
     * 
     * @return id do tipo de bebida.
     */
    
    public Long getId() {
        return id;
    }
    
    /**
     * Define o identificador do tipo de bebida.
     * 
     * @param id Novo identificador do tipo de bebida.
     */
    
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * Obtém a descrição do tipo de bebida (ex: "Alcoólica", "Não Alcoólica").
     * 
     * @return o tipo de bebida.
     */
    
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Define a descrição do tipo de bebida.
     * 
     * @param tipo Nova descrição do tipo de bebida.
     */
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
