package feastplannerecalc.model;

import jakarta.persistence.*;

/**
 * Classe que representa a quantidade padrão consumida por pessoa de cada bebida.
 * Esta classe armazena a quantidade padrão que cada pessoa consome de uma bebida específica.
 * Ela recebe a chave estrangeira da tabela "bebidas" e armazena a quantidade correspondente.
 * 
 * A tabela mapeada é "bebidas_quantidade_padrao".
 */

@Entity
@Table(name = "bebidas_quantidade_padrao")
public class BebidaQuantidadePadrao {
	
	/**
     * Identificador único da quantidade padrão.
     * Gerado automaticamente pelo banco de dados.
     */
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Referência para a bebida associada, representada pela chave estrangeira
     * que mapeia a tabela "bebidas".
     * Este campo é obrigatório.
     */
    
    @ManyToOne
    @JoinColumn(name = "id_bebidas", nullable = false)
    private Bebida bebida;
    
    /**
     * Quantidade padrão consumida por pessoa desta bebida.
     * Este campo é obrigatório.
     */
    
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;


    // Getters e Setters

    /**
     * Obtém o identificador da quantidade padrão.
     * 
     * @return id da quantidade padrão.
     */
    
    public Long getId() {
        return id;
    }
    
    /**
     * Define o identificador da quantidade padrão.
     * 
     * @param id Novo identificador da quantidade padrão.
     */
    
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Obtém a bebida associada a essa quantidade padrão.
     * 
     * @return a bebida.
     */

    public Bebida getBebida() {
        return bebida;
    }
    
    /**
     * Define a bebida associada a essa quantidade padrão.
     * 
     * @param bebida Nova bebida associada.
     */
    
    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }
    
    /**
     * Obtém a quantidade padrão consumida por pessoa dessa bebida.
     * 
     * @return quantidade consumida.
     */
    
    public Integer getQuantidade() {
        return quantidade;
    }


    /**
     * Define a quantidade padrão consumida por pessoa dessa bebida.
     * 
     * @param quantidade Nova quantidade consumida.
     */
    
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
