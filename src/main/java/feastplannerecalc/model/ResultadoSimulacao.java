package feastplannerecalc.model;

import java.util.ArrayList;
import java.util.List;


import feastplannerecalc.database.HibernateUtil;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Classe que armazena o resultado da simulação de um evento,
 * incluindo a quantidade de pessoas por categoria e o tipo de comida oferecida.
 * A tabela mapeada é "resultado_simulacao".
 */
@Entity
@Table(name = "resultado_simulacao")
public class ResultadoSimulacao {

    /**
     * Identificador único da simulação.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo de comida oferecida no evento.
     * É uma chave estrangeira que referencia a tabela "comidas_tipo".
     */
    @ManyToOne
    @JoinColumn(name = "id_tipo_comida", nullable = false)
    private ComidaTipo tipoComida;

    /**
     * Quantidade de homens presentes no evento.
     * Este campo é obrigatório.
     */
    @Column(name = "quantidade_homens", nullable = false)
    private Integer quantidadeHomens;

    /**
     * Quantidade de mulheres presentes no evento.
     * Este campo é obrigatório.
     */
    @Column(name = "quantidade_mulheres", nullable = false)
    private Integer quantidadeMulheres;

    /**
     * Quantidade de crianças presentes no evento.
     * Este campo é obrigatório.
     */
    @Column(name = "quantidade_criancas", nullable = false)
    private Integer quantidadeCriancas;

    /**
     * Quantidade de comilões (pessoas que comem mais que a média) presentes no evento.
     * Este campo é obrigatório.
     */
    @Column(name = "quantidade_comiloes", nullable = false)
    private Integer quantidadeComiloes;

    /**
     * Quantidade de pessoas que não comem carne presentes no evento.
     * Este campo é obrigatório.
     */
   // @Column(name = "quantidade_nao_comem_carne", nullable = false)
   // private Integer quantidadeNaoComemCarne;

    // Getters e Setters

    /**
     * Obtém o identificador da simulação.
     * 
     * @return id da simulação.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o identificador da simulação.
     * 
     * @param id Novo identificador da simulação.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o tipo de comida oferecida na simulação.
     * 
     * @return tipo de comida.
     */
    public ComidaTipo getTipoComida() {
        return tipoComida;
    }

    /**
     * Define o tipo de comida oferecida na simulação.
     * 
     * @param tipoComida Novo tipo de comida.
     */
    public void setTipoComida(ComidaTipo tipoComida) {
        this.tipoComida = tipoComida;
    }

    /**
     * Obtém a quantidade de homens no evento.
     * 
     * @return quantidade de homens.
     */
    public Integer getQuantidadeHomens() {
        return quantidadeHomens;
    }

    /**
     * Define a quantidade de homens no evento.
     * 
     * @param quantidadeHomens Nova quantidade de homens.
     */
    public void setQuantidadeHomens(Integer quantidadeHomens) {
        this.quantidadeHomens = quantidadeHomens;
    }

    /**
     * Obtém a quantidade de mulheres no evento.
     * 
     * @return quantidade de mulheres.
     */
    public Integer getQuantidadeMulheres() {
        return quantidadeMulheres;
    }

    /**
     * Define a quantidade de mulheres no evento.
     * 
     * @param quantidadeMulheres Nova quantidade de mulheres.
     */
    public void setQuantidadeMulheres(Integer quantidadeMulheres) {
        this.quantidadeMulheres = quantidadeMulheres;
    }

    /**
     * Obtém a quantidade de crianças no evento.
     * 
     * @return quantidade de crianças.
     */
    public Integer getQuantidadeCriancas() {
        return quantidadeCriancas;
    }

    /**
     * Define a quantidade de crianças no evento.
     * 
     * @param quantidadeCriancas Nova quantidade de crianças.
     */
    public void setQuantidadeCriancas(Integer quantidadeCriancas) {
        this.quantidadeCriancas = quantidadeCriancas;
    }

    /**
     * Obtém a quantidade de comilões no evento.
     * 
     * @return quantidade de comilões.
     */
    public Integer getQuantidadeComiloes() {
        return quantidadeComiloes;
    }

    /**
     * Define a quantidade de comilões no evento.
     * 
     * @param quantidadeComiloes Nova quantidade de comilões.
     */
    public void setQuantidadeComiloes(Integer quantidadeComiloes) {
        this.quantidadeComiloes = quantidadeComiloes;
    }

    /**
     * Obtém a quantidade de pessoas que não comem carne no evento.
     * 
     * @return quantidade de pessoas que não comem carne.
     */
   // public Integer getQuantidadeNaoComemCarne() {
  //      return quantidadeNaoComemCarne;
  //  }

    /**
     * Define a quantidade de pessoas que não comem carne no evento.
     * 
     * @param quantidadeNaoComemCarne Nova quantidade de pessoas que não comem carne.
     */
  //  public void setQuantidadeNaoComemCarne(Integer quantidadeNaoComemCarne) {
     //   this.quantidadeNaoComemCarne = quantidadeNaoComemCarne;
   // }
    
    public static List<ResultadoSimulacao> carregarResultadosSimulacao() {
        List<ResultadoSimulacao> listaResultados = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            listaResultados = session.createQuery("FROM ResultadoSimulacao", ResultadoSimulacao.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return listaResultados;
    }
    
    public static void excluirPorId(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "DELETE FROM ResultadoSimulacao WHERE id = :id";
            session.createQuery(hql)
                   .setParameter("id", id)
                   .executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao excluir ResultadoSimulacao: " + e.getMessage(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
