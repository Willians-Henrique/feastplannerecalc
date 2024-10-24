package feastplannerecalc.model;

import java.util.ArrayList;
import java.util.List;

import feastplannerecalc.database.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.EntityManager;

/**
 * Classe que representa a quantidade padrão de comida consumida por diferentes
 * categorias de pessoas (ex: crianças, adultos, comilões) em eventos de
 * churrasco ou salgados.
 * 
 * A tabela mapeada é "comidas_quantidade_padrao".
 */
@Entity
@Table(name = "comidas_quantidade_padrao")
public class ComidaQuantidadePadrao {

    /**
     * Identificador único da quantidade padrão de comida.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Categoria de pessoas associada a essa quantidade de comida (ex: criança, adulto).
     * A chave estrangeira referenciando a tabela de pessoas categorias.
     * Este campo é obrigatório.
     */
    @ManyToOne
    @JoinColumn(name = "id_pessoas_categoria", nullable = false)
    private PessoaCategoria pessoasCategoria;

    /**
     * Quantidade padrão de carne consumida por essa categoria de pessoa.
     * Este campo é obrigatório.
     */
    @Column(name = "quantidade_carne", nullable = false)
    private Integer quantidadeCarne;

    /**
     * Quantidade padrão de salgado consumido por essa categoria de pessoa.
     * Este campo é obrigatório.
     */
    @Column(name = "quantidade_salgado", nullable = false)
    private Integer quantidadeSalgado;

    // Getters e Setters

    /**
     * Obtém o identificador único da quantidade padrão de comida.
     * 
     * @return id da quantidade de comida.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador único da quantidade padrão de comida.
     * 
     * @param id Novo identificador da quantidade de comida.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém a categoria de pessoas associada a essa quantidade de comida.
     * 
     * @return categoria de pessoas.
     */
    public PessoaCategoria getPessoasCategoria() {
        return pessoasCategoria;
    }

    /**
     * Define a categoria de pessoas associada a essa quantidade de comida.
     * 
     * @param pessoasCategoria Nova categoria de pessoas.
     */
    public void setPessoasCategoria(PessoaCategoria pessoasCategoria) {
        this.pessoasCategoria = pessoasCategoria;
    }

    /**
     * Obtém a quantidade padrão de carne consumida por essa categoria de pessoa.
     * 
     * @return quantidade de carne.
     */
    public Integer getQuantidadeCarne() {
        return quantidadeCarne;
    }

    /**
     * Define a quantidade padrão de carne consumida por essa categoria de pessoa.
     * 
     * @param quantidadeCarne Nova quantidade de carne.
     */
    public void setQuantidadeCarne(Integer quantidadeCarne) {
        this.quantidadeCarne = quantidadeCarne;
    }

    /**
     * Obtém a quantidade padrão de salgados consumida por essa categoria de pessoa.
     * 
     * @return quantidade de salgados.
     */
    public Integer getQuantidadeSalgado() {
        return quantidadeSalgado;
    }

    /**
     * Define a quantidade padrão de salgados consumida por essa categoria de pessoa.
     * 
     * @param quantidadeSalgado Nova quantidade de salgados.
     */
    public void setQuantidadeSalgado(Integer quantidadeSalgado) {
        this.quantidadeSalgado = quantidadeSalgado;
    }
    
    public static List<ComidaQuantidadePadrao> carregarQuantidadeCarnePorPessoa() {
        List<ComidaQuantidadePadrao> listaQuantidades = new ArrayList<>(); // Inicializa a lista
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            listaQuantidades = session.createQuery("FROM ComidaQuantidadePadrao", ComidaQuantidadePadrao.class).list();
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return listaQuantidades; // Retorna a lista
    }
    @Override
    public String toString() {
        return quantidadeCarne != null ? quantidadeCarne.toString() : "Sem valor";
    }


}
